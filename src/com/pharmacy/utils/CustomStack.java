package com.pharmacy.utils;

import java.util.ArrayList;
import java.util.List;

public class CustomStack<T> {
    private final List<T> items = new ArrayList<>();
    
    public void push(T item) {
        items.add(item);
    }
    
    public T pop() {
        if (isEmpty()) return null;
        return items.remove(items.size() - 1);
    }
    
    public T peek() {
        if (isEmpty()) return null;
        return items.get(items.size() - 1);
    }
    
    public boolean isEmpty() {
        return items.isEmpty();
    }
    
    public int size() {
        return items.size();
    }
    
    public List<T> getTopItems(int count) {
        if (count <= 0) return new ArrayList<>();
        int start = Math.max(0, items.size() - count);
        return new ArrayList<>(items.subList(start, items.size()));
    }
}