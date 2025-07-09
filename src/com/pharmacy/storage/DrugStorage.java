package com.pharmacy.storage;

import com.pharmacy.models.Drug;

import java.io.*;
import java.util.ArrayList;
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
        System.out.println("📥 Loading drugs from: " + filePath);
        List<Drug> drugs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                Drug drug = Drug.fromCSV(line);
                if (drug != null) {
                    drugs.add(drug);
                }
            }

        } catch (IOException e) {
            System.out.println("❌ Failed to load drugs: " + e.getMessage());
        }

        return drugs;
    }

    /**
     * Save drugs to CSV file
     * @param drugs List of drugs to save
     */
    public void saveDrugs(List<Drug> drugs) {
        System.out.println("💾 Saving drugs to: " + filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("code,name,supplierIds,quantity,price,expiryDate");
            writer.newLine();

            for (Drug d : drugs) {
                writer.write(d.toCSV());
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("❌ Error saving drugs: " + e.getMessage());
        }
    }
}
