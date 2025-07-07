package com.pharmacy.utils;

import com.pharmacy.models.Drug;

/**
 * Min-heap implementation for stock monitoring
 */
public class CustomHeap {
    private Drug[] heap;
    private int size;
    private int capacity;
    
    public CustomHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new Drug[capacity];
        this.size = 0;
    }
    
    public void add(Drug drug) {
        // TODO: Implement heap insert
    }
    
    public Drug removeMin() {
        // TODO: Implement extract min
        return null;
    }
    
    // TODO: Add heapify methods
}