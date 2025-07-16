package com.pharmacy.models;

import java.time.LocalDateTime;
import com.pharmacy.utils.CSVUtils;

public class Transaction {
    private String id;
    private String drugCode;
    private String customerId;
    private int quantity;
    private LocalDateTime date;
    private double totalCost;        // ← new field

    // Updated constructor to accept totalCost
    public Transaction(String id,
                       String drugCode,
                       String customerId,
                       int quantity,
                       double totalCost) {
        this.id = id;
        this.drugCode = drugCode;
        this.customerId = customerId;
        this.quantity = quantity;
        this.totalCost = totalCost;
        this.date = LocalDateTime.now();
    }

    // Getters
    public String getId()             { return id; }
    public String getDrugCode()       { return drugCode; }
    public String getCustomerId()     { return customerId; }
    public int    getQuantity()       { return quantity; }
    public LocalDateTime getDate()    { return date; }
    public double getTotalCost()      { return totalCost; }  // ← new getter

    // Setters
    public void setDate(LocalDateTime date)       { this.date = date; }
    public void setTotalCost(double totalCost)    { this.totalCost = totalCost; }

    // For CSV import (parses the stored date)
    public void setDateFromString(String dateStr) {
        this.date = CSVUtils.parseDateTime(dateStr);
    }

    @Override
    public String toString() {
        // id,drugCode,customerId,quantity,ISODateTime,totalCost
        return String.format("%s,%s,%s,%d,%s,%.2f",
            id,
            drugCode,
            customerId,
            quantity,
            CSVUtils.formatDateTime(date),
            totalCost
        );
    }
}
