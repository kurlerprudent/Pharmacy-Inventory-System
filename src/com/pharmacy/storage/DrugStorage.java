package com.pharmacy.storage;

import com.pharmacy.models.Drug;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
        //System.out.println("Loading drugs from: " + filePath);
        List<Drug> drugs  = new ArrayList<Drug>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while((line = reader.readLine())!=null){
                 if (isFirstLine) {
                 isFirstLine = false;
                 continue;
                }
                String parts[] = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                if(parts.length >=6){
                    String code = parts[0].trim();
                    String name = parts[1].trim();
                    String supplierRaw = parts[2].trim().replace("\"", "");
                    List<String> supplierIds = Arrays.asList(supplierRaw.split(","));
                    int quantity = Integer.parseInt(parts[3].trim());
                    double price = Double.parseDouble(parts[4].trim());
                    LocalDate expiryDate = LocalDate.parse(parts[5].trim());

                    Drug drug = new Drug(code,name,supplierIds,quantity,price,expiryDate);
                    drugs.add(drug);

                }
            }

            
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        return drugs; // Return actual list
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