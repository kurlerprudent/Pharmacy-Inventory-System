package com.pharmacy.utils;

import java.util.*;

public class CustomHeap<T> {
    private final List<T> heap = new ArrayList<>();
    private final Comparator<T> comparator;
    
    public CustomHeap(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    
    public void insert(T item) {
        heap.add(item);
        heapifyUp(heap.size() - 1);
    }
    
    public T peek() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }
    
    public T extractMin() {
        if (heap.isEmpty()) return null;
        T min = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyDown(0);
        }
        return min;
    }
    
    public void remove(T item) {
        int index = heap.indexOf(item);
        if (index == -1) return;
        
        T last = heap.remove(heap.size() - 1);
        if (index < heap.size()) {
            heap.set(index, last);
            heapifyDown(index);
            heapifyUp(index);
        }
    }
    
    public void heapify() {
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }
    
    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (comparator.compare(heap.get(index), heap.get(parent)) >= 0) break;
            swap(index, parent);
            index = parent;
        }
    }
    
    private void heapifyDown(int index) {
        int smallest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        
        if (left < heap.size() && 
            comparator.compare(heap.get(left), heap.get(smallest)) < 0) {
            smallest = left;
        }
        
        if (right < heap.size() && 
            comparator.compare(heap.get(right), heap.get(smallest)) < 0) {
            smallest = right;
        }
        
        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }
    }
    
    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}