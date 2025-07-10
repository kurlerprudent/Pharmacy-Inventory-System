package com.pharmacy.utils;

import java.util.LinkedList;
import java.util.List;

public class CustomQueue<T> {
    private final LinkedList<T> list = new LinkedList<>();
    
    public void enqueue(T item) {
        list.addLast(item);
    }
    
    public T dequeue() {
        if (isEmpty()) return null;
        return list.removeFirst();
    }
    
    public T peek() {
        if (isEmpty()) return null;
        return list.getFirst();
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public int size() {
        return list.size();
    }
    
    public List<T> getAll() {
        return new LinkedList<>(list);
    }
}