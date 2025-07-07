package com.pharmacy.models;

import java.util.Date;
import java.util.List;

/**
 * Represents a drug/medicine in the inventory
 * Properties: code, name, suppliers, quantity, price, expiry date
 */
public class Drug {
    private String code;
    private String name;
    private List<String> supplierIds; // List of supplier IDs
    private int quantity;
    private double price;
    private Date expiryDate;

    // Constructor
    public Drug(String code, String name, List<String> supplierIds, 
                int quantity, double price, Date expiryDate) {
        this.code = code;
        this.name = name;
        this.supplierIds = supplierIds;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    // Getters and setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    // TODO: Add other getters/setters
    // TODO: Implement validation methods (e.g., isExpired())
}