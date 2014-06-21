package com.skiplist;

interface SkippableList<T extends Comparable<? super T>> {
    int LEVELS = 5;

    boolean delete(T target);
    void print();
    void insert(T data);
    SkipNode<T> search(T data);
}
