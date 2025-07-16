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
            for (String[] r : records) {
                if (r.length < 5) continue;
                Supplier s = new Supplier(
                    r[0],
                    r[1],
                    r[2],
                    r[3],
                    Integer.parseInt(r[4])
                );
                supplierMap.put(s.getId(), s);
            }
        } catch (IOException e) {
            System.err.println("Error loading suppliers: " + e.getMessage());
        }
    }

    private void saveToCSV(Supplier supplier) {
        try {
            CSVUtils.appendToCSV("data/suppliers.csv", supplier.toString().split(",", 5));
        } catch (IOException e) {
            System.err.println("Error saving supplier: " + e.getMessage());
        }
    }

    public void addSupplier(Supplier s) {
        supplierMap.put(s.getId(), s);
        saveToCSV(s);
    }

    public Supplier getSupplier(String id) {
        return supplierMap.get(id);
    }

    public void updateSupplier(Supplier s) {
        supplierMap.put(s.getId(), s);
        // for simplicity we’re not rewriting CSV here
    }

    public void removeSupplier(String id) {
        supplierMap.remove(id);
    }

    public List<Supplier> getAllSuppliers() {
        return new ArrayList<>(supplierMap.values());
    }
}
