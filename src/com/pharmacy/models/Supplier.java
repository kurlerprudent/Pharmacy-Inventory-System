package com.pharmacy.models;

/**
 * Represents a drug supplier
 * Properties: ID, name, location, delivery time
 */
public class Supplier {
    private String id;
    private String name;
    private String location;
    private int deliveryTime; // in days
    
    public Supplier(String id, String name, String location, int deliveryTime) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.deliveryTime = deliveryTime;
    }
    
    // TODO: Add getters/setters
}