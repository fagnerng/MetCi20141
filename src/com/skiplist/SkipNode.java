package com.skiplist;

class SkipNode<T extends Comparable<? super T>> {

	T data;
	@SuppressWarnings("unchecked")
	SkipNode<T>[] next = (SkipNode<T>[]) new SkipNode[SkippableList.LEVELS];

	public SkipNode(T data) {
		this.data = data;
	}

	public void refreshAfterDelete(int level) {
		SkipNode<T> current = this.getNext(level);
		while (current != null && current.getNext(level) != null) {
			if (current.getNext(level).data == null) {
				SkipNode<T> successor = current.getNext(level).getNext(level);
				current.setNext(successor, level);
				return;
			}

			current = current.getNext(level);
		}
	}

	public void setNext(SkipNode<T> next, int level) {
		this.next[level] = next;
	}

	public SkipNode<T> getNext(int level) {
		return this.next[level];
	}

	public SkipNode<T> search(T data, int level) {
		SkipNode<T> result = null;
		SkipNode<T> current = this.getNext(level);
		while (current != null && current.data.compareTo(data) < 1) {
			if (current.data.equals(data)) {
				result = current;
				break;
			}

			current = current.getNext(level);
		}

		return result;
	}

	public void insert(SkipNode<T> SkipNode, int level) {
		SkipNode<T> current = this.getNext(level);
		if (current == null) {
			this.setNext(SkipNode, level);
			return;
		}

		if (SkipNode.data.compareTo(current.data) < 1) {
			this.setNext(SkipNode, level);
			SkipNode.setNext(current, level);
			return;
		}

		while (current.getNext(level) != null
				&& current.data.compareTo(SkipNode.data) < 1
				&& current.getNext(level).data.compareTo(SkipNode.data) < 1) {

			current = current.getNext(level);
		}

		SkipNode<T> successor = current.getNext(level);
		current.setNext(SkipNode, level);
		SkipNode.setNext(successor, level);
	}

	public void print(int level) {
		System.out.print("level " + level + ": [");
		int length = 0;
		SkipNode<T> current = this.getNext(level);
		while (current != null) {
			length++;
			System.out.print(current.data.toString() + " ");
			current = current.getNext(level);
		}

		System.out.println("], length: " + length);
	}

}