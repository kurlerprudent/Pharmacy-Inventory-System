
package com.pharmacy.logic;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pharmacy.models.Drug;
import com.pharmacy.models.Supplier;

public class SupplierMapping {
   private Map<String, Drug> drugs = new HashMap<>();
    private Map<String, List<Supplier>> drugSuppliers = new HashMap<>();
    private final String FILE_NAME = "suppliers.txt";

    // Add drug to system
    public void addDrug(String code, String name) {
        drugs.putIfAbsent(code, new Drug(code, name, null, 0, 0, null));
    }

    // Add supplier to drug
    public void addSupplierToDrug(String drugCode, Supplier supplier) {
        drugSuppliers.putIfAbsent(drugCode, new ArrayList<>());
        drugSuppliers.get(drugCode).add(supplier);
        saveToFile();
    }

    // View suppliers for a drug
    public List<Supplier> getSuppliersForDrug(String drugCode) {
        return drugSuppliers.getOrDefault(drugCode, new ArrayList<>());
    }

    // Filter by location
    public List<Supplier> filterByLocation(String location) {
        List<Supplier> result = new ArrayList<>();
        for (List<Supplier> list : drugSuppliers.values()) {
            for (Supplier s : list) {
                if (s.location.equalsIgnoreCase(location)) result.add(s);
            }
        }
        return result;
    }

    // Filter by delivery days
    public List<Supplier> filterByDeliveryTime(int maxDays) {
        List<Supplier> result = new ArrayList<>();
        for (List<Supplier> list : drugSuppliers.values()) {
            for (Supplier s : list) {
                if (s.deliveryTime <= maxDays) result.add(s);
            }
        }
        return result;
    }

    // Save to file
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, List<Supplier>> entry : drugSuppliers.entrySet()) {
                String code = entry.getKey();
                Drug drug = drugs.get(code);
                for (Supplier s : entry.getValue()) {
                    writer.write(code + "," + drug.name + "," + s.name + "," + s.location + "," + s.deliveryTime);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }

    // Load from file
    public void loadFromFile() {
        drugSuppliers.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);
                if (parts.length == 5) {
                    String code = parts[0];
                    String name = parts[1];
                    Supplier supplier = new Supplier(parts[2], parts[3], name, Integer.parseInt(parts[4]));
                    addDrug(code, name);
                    addSupplierToDrugNoSave(code, supplier);
                }
            }
        } catch (IOException e) {
            System.out.println("No saved data found. Starting fresh.");
        }
    }

    private void addSupplierToDrugNoSave(String drugCode, Supplier supplier) {
        drugSuppliers.putIfAbsent(drugCode, new ArrayList<>());
        drugSuppliers.get(drugCode).add(supplier);
    }

    public void listDrugs() {
        if (drugs.isEmpty()) {
            System.out.println("No drugs available.");
            return;
        }
        for (Drug d : drugs.values()) {
            System.out.println(d);
        }
    }
}