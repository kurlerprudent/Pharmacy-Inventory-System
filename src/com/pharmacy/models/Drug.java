package com.pharmacy.models;

import java.time.LocalDate;

public class Drug {
    private String code;
    private String name;
    private int quantity;
    private double price;
    private LocalDate expiry;
    private String supplierId;

    public Drug(String code, String name, int quantity, double price, LocalDate expiry, String supplierId) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.expiry = expiry;
        this.supplierId = supplierId;
    }

    // Getters
    public String getCode() { return code; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public LocalDate getExpiry() { return expiry; }
    public String getSupplierId() { return supplierId; }
    
    // Setters
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
    public void setExpiry(LocalDate expiry) { this.expiry = expiry; }
    public void setSupplierId(String supplierId) { this.supplierId = supplierId; }
    
    @Override
    public String toString() {
        return String.format("%s,%s,%d,%.2f,%s,%s", 
            code, name, quantity, price, expiry, supplierId);
    }
}