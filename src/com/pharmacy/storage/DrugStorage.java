package com.pharmacy.storage;

import com.pharmacy.models.Drug;
import java.util.List;

/**
 * Handles loading/saving drugs from/to CSV file
 * Must implement manual CSV parsing (no libraries)
 */
public class DrugStorage {
    private String filePath;
    
    public DrugStorage(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * Load drugs from CSV file into memory
     * @return List of Drug objects
     */
    public List<Drug> loadDrugs() {
        // TODO: Implement manual CSV parsing
        // Steps:
        // 1. Open file using FileReader
        // 2. Read line by line
        // 3. Split each line by comma
        // 4. Create Drug objects
        // 5. Handle date parsing manually
        System.out.println("Loading drugs from: " + filePath);
        return null; // Return actual list
    }
    
    /**
     * Save drugs to CSV file
     * @param drugs List of drugs to save
     */
    public void saveDrugs(List<Drug> drugs) {
        // TODO: Convert Drug objects to CSV format
        // 1. Create CSV header
        // 2. Convert each Drug to CSV line
        // 3. Write to file
        System.out.println("Saving drugs to: " + filePath);
    }
}