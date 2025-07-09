package com.pharmacy.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    // Date formatter for file I/O
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

    // Getters
    public String getCode() { return code; }
    public String getName() { return name; }
    public List<String> getSupplierIds() { return supplierIds; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public LocalDate getExpiryDate() { return expiryDate; }

    // Setters
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setSupplierIds(List<String> supplierIds) { this.supplierIds = supplierIds; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    // Utility: Check if the drug is expired
    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }

    // CSV formatting for file I/O
    public String toCSV() {
        return String.format("%s,%s,%s,%d,%.2f,%s",
                code,
                name,
                String.join("|", supplierIds),
                quantity,
                price,
                expiryDate.format(formatter));
    }

    public static Drug fromCSV(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length != 6) return null;

            String code = parts[0];
            String name = parts[1];
            List<String> supplierIds = List.of(parts[2].split("\\|"));
            int quantity = Integer.parseInt(parts[3]);
            double price = Double.parseDouble(parts[4]);
            LocalDate expiryDate = LocalDate.parse(parts[5], formatter);

            return new Drug(code, name, supplierIds, quantity, price, expiryDate);
        } catch (Exception e) {
            return null;
        }
    }

    // Display drug info
    @Override
    public String toString() {
        return String.format("%s (%s)\n  Qty: %d | Price: %.2f | Exp: %s",
                name, code, quantity, price, expiryDate.format(formatter));
    }

    // Compare by quantity (for priority queue)
    @Override
    public int compareTo(Drug other) {
        return Integer.compare(this.quantity, other.quantity);
    }
}
