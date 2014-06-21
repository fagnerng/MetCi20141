package com.skiplist;

import java.util.Random;

public class SkipList<T extends Comparable<? super T>> implements SkippableList<T> {

     private final SkipNode<T> head = new SkipNode<>(null);
    private final Random rand = new Random();

    @Override
    public void insert(T data) {
        SkipNode<T> SkipNode = new SkipNode<>(data);
        for (int i = 0; i < LEVELS; i++) {
            if (rand.nextInt((int) Math.pow(2, i)) == 0) { //insert with prob = 1/(2^i)
                insert(SkipNode, i);
            }
        }
    }

    @Override
    public boolean delete(T target) {
        System.out.println("Deleting " + target.toString());
        SkipNode<T> victim = search(target);
        if (victim == null) return false;
        victim.data = null;

        for (int i = 0; i < LEVELS; i++) {
            head.refreshAfterDelete(i);
        }

        System.out.println();
        return true;
    }




    @Override
    public void print() {
        for (int i = 0; i < LEVELS; i++) {
            head.print(i);
        }

        System.out.println();
    }

    private void insert(SkipNode<T> SkipNode, int level) {
        head.insert(SkipNode, level);
    }
    @Override
    public SkipNode<T> search(T data) {
        SkipNode<T> result = null;
        for (int i = LEVELS-1; i >= 0; i--) {
            if ((result = head.search(data, i)) != null) {
                break;
            }
        }

        return result;
    }

}
