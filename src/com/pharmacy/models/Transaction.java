package com.pharmacy.models;

import java.time.LocalDateTime;

import com.pharmacy.utils.CSVUtils;

public class Transaction {
    private String id;
    private String drugCode;
    private String customerId;
    private int quantity;
    private LocalDateTime date;

    public Transaction(String id, String drugCode, String customerId, int quantity) {
        this.id = id;
        this.drugCode = drugCode;
        this.customerId = customerId;
        this.quantity = quantity;
        this.date = LocalDateTime.now();
    }

    // Getters and setters
    public String getId() { return id; }
    public String getDrugCode() { return drugCode; }
    public String getCustomerId() { return customerId; }
    public int getQuantity() { return quantity; }
    public LocalDateTime getDate() { return date; }
       public void setDateFromString(String dateStr) {
        this.date = CSVUtils.parseDateTime(dateStr);
    }
    
    public void setId(String id) { this.id = id; }
    public void setDrugCode(String drugCode) { this.drugCode = drugCode; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setDate(LocalDateTime date) { this.date = date; }
}