package com.pharmacy.models;

import java.util.Date;

/**
 * Represents a purchase transaction
 */
public class Transaction {
    private String drugCode;
    private int quantity;
    private Date timestamp;
    private String buyerId;
    private double totalCost;
    
    public Transaction(String drugCode, int quantity, Date timestamp, 
                       String buyerId, double totalCost) {
        this.drugCode = drugCode;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.buyerId = buyerId;
        this.totalCost = totalCost;
    }
    
    // TODO: Add getters/setters
}