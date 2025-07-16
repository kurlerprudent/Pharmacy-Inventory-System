package com.pharmacy.models;

import java.time.LocalDate;
import java.util.List;
import java.util.StringJoiner;

public class Drug {
    private String code;
    private String name;
    private int quantity;
    private double price;
    private LocalDate expiry;
    private List<String> supplierIds;  // ← now a list

    public Drug(String code,
                String name,
                int quantity,
                double price,
                LocalDate expiry,
                List<String> supplierIds) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.expiry = expiry;
        this.supplierIds = supplierIds;
    }

    // Getters
    public String getCode() { return code; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public LocalDate getExpiry() { return expiry; }
    public List<String> getSupplierIds() { return supplierIds; }

    // Setters
    public void setCode(String code) { this.code = code; }
    public void setName(String name) { this.name = name; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
    public void setExpiry(LocalDate expiry) { this.expiry = expiry; }
    public void setSupplierIds(List<String> supplierIds) { this.supplierIds = supplierIds; }

    @Override
    public String toString() {
        // join supplier IDs with semicolons
        StringJoiner sj = new StringJoiner(";");
        for (String s : supplierIds) sj.add(s);
        return String.format("%s,%s,%d,%.2f,%s,%s",
            code, name, quantity, price, expiry, sj.toString());
    }
}
