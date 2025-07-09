package com.pharmacy.models;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a drug/medicine in the inventory
 * Properties: code, name, suppliers, quantity, price, expiry date
 */
public class Drug implements Comparable<Drug> {
    private String code;
    private String name;
    private List<String> supplierIds;
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

    // === Getters ===
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<String> getSupplierIds() {
        return supplierIds;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    // === Setters ===
    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSupplierIds(List<String> supplierIds) {
        this.supplierIds = supplierIds;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    // For priority queue (stock-based ordering)
    @Override
    public int compareTo(Drug other) {
        return Integer.compare(this.quantity, other.quantity);
    }

    @Override
    public String toString() {
        return name + " (" + code + ") - " + quantity + " units";
    }
}
