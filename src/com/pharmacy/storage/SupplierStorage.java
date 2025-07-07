package com.pharmacy.storage;

import com.pharmacy.models.Supplier;
import java.util.List;

public class SupplierStorage {
    private String filePath;
    
    public SupplierStorage(String filePath) {
        this.filePath = filePath;
    }
    
    public List<Supplier> loadSuppliers() {
        // TODO: Implement manual CSV parsing
        return null;
    }
    
    public void saveSuppliers(List<Supplier> suppliers) {
        // TODO: Save suppliers to CSV
    }
}