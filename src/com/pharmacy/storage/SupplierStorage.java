package com.pharmacy.storage;

import com.pharmacy.models.Supplier;
import com.pharmacy.utils.CSVUtils;
import java.io.IOException;
import java.util.*;

public class SupplierStorage {
    private final Map<String, Supplier> supplierMap = new HashMap<>();
    
    public SupplierStorage() {
        loadFromCSV();
    }
    
    private void loadFromCSV() {
        try {
            List<String[]> records = CSVUtils.readCSV("data/suppliers.csv");
            for (String[] record : records) {
                if (record.length < 3) continue;
                addSupplier(new Supplier(record[0], record[1], record[2]));
            }
        } catch (IOException e) {
            System.err.println("Error loading suppliers from CSV: " + e.getMessage());
        }
    }
    
    private void saveToCSV(Supplier supplier) {
        try {
            CSVUtils.appendToCSV("data/suppliers.csv", new String[]{
                supplier.getId(),
                supplier.getName(),
                supplier.getContact()
            });
        } catch (IOException e) {
            System.err.println("Error saving supplier to CSV: " + e.getMessage());
        }
    }

    public void addSupplier(Supplier supplier) {
        supplierMap.put(supplier.getId(), supplier);
        saveToCSV(supplier);
    }

    public Supplier getSupplier(String id) {
        return supplierMap.get(id);
    }

    public void updateSupplier(Supplier supplier) {
        supplierMap.put(supplier.getId(), supplier);
    }

    public void removeSupplier(String id) {
        supplierMap.remove(id);
    }

    public List<Supplier> getAllSuppliers() {
        return new ArrayList<>(supplierMap.values());
    }
}