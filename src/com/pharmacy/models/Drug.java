package com.pharmacy.models;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Represents a drug/medicine in the inventory
 * Properties: code, name, suppliers, quantity, price, expiry date
 */
public class Drug implements Comparable<Drug> {
    private String code;
    private String name;
    private List<String> supplierIds; // List of supplier IDs
    private int quantity;
    private double price;
    private LocalDate expiryDate;

    // Constructor
    public Drug(String code, String name, List<String> supplierIds, 
                int quantity, double price, LocalDate expiryDate) {
        this.code = code;
        this.name = name;
        this.supplierIds = supplierIds;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    // Getters and setters
    public String getCode() { return code; }
    public int getQuantity() {return quantity;}
    public void setCode(String code) { this.code = code; }
    

    // This method tells java priorityqueue to order drugs whose stock level is below the stock threshold by their quantity 
    @Override
    public int compareTo(Drug other){
        return Integer.compare(this.quantity, other.quantity);

    }

    public String toString(){
        return name + "(" + code + ") -" +quantity;
    }
    
    // TODO: Add other getters/setters
    // TODO: Implement validation methods (e.g., isExpired())
}