package com.pharmacy.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a drug sale transaction
 */
public class Transaction {
    private String drugCode;
    private int quantity;
    private double totalCost;
    private String buyerId;
    private String timestamp;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Transaction(String drugCode, int quantity, double totalCost, String buyerId) {
        this.drugCode = drugCode;
        this.quantity = quantity;
        this.totalCost = totalCost;
        this.buyerId = buyerId;
        this.timestamp = LocalDateTime.now().format(DATE_FORMAT);
    }

    public String getDrugCode() {
        return drugCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDate() {
        // Return just the date part for filtering
        return timestamp.split(" ")[0];
    }

    public String toCSV() {
        return String.format("%s,%d,%.2f,%s,%s",
                drugCode, quantity, totalCost, buyerId, timestamp);
    }

    public static Transaction fromCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) return null;

        Transaction tx = new Transaction(
                parts[0],
                Integer.parseInt(parts[1]),
                Double.parseDouble(parts[2]),
                parts[3]
        );
        tx.timestamp = parts[4]; // override auto-generated timestamp with stored one
        return tx;
    }

    @Override
    public String toString() {
        return String.format("[%s] Buyer: %s | Drug: %s | Qty: %d | Total: %.2f",
                timestamp, buyerId, drugCode, quantity, totalCost);
    }
}
