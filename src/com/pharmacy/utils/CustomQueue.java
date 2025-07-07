package com.pharmacy.utils;

/**
 * Custom queue implementation for purchase history
 */
public class CustomQueue<T> {
    private final Object[] elements;
    private int front = 0;
    private int rear = -1;
    private int size = 0;
    
    public CustomQueue(int capacity) {
        elements = new Object[capacity];
    }
    
    public void enqueue(T element) {
        if (size == elements.length) {
            // Handle queue full - maybe remove oldest?
            return;
        }
        rear = (rear + 1) % elements.length;
        elements[rear] = element;
        size++;
    }
    
    public T dequeue() {
        if (size == 0) return null;
        T element = (T) elements[front];
        front = (front + 1) % elements.length;
        size--;
        return element;
    }
    
    // TODO: Add peek, isEmpty, getRecentTransactions methods
}