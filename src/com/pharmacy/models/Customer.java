package com.pharmacy.models;

import java.util.Queue;
import com.pharmacy.utils.CustomQueue;

/**
 * Represents a pharmacy customer
 * Uses CustomQueue for purchase history
 */
public class Customer {
    private String id;
    private String name;
    private String contact;
    private CustomQueue<Transaction> purchaseHistory;
    
    public Customer(String id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.purchaseHistory = new CustomQueue<>(50); // Capacity for 50 transactions
    }
    
    // TODO: Add getters/setters
    // TODO: Add method to add transaction to history
}