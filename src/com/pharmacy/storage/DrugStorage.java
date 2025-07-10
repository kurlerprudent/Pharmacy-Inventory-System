package com.pharmacy.storage;

import com.pharmacy.models.Drug;
import com.pharmacy.utils.CSVUtils;
import com.pharmacy.utils.CustomHeap;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class DrugStorage {
    private final Map<String, Drug> drugMap = new HashMap<>();
    private final Map<String, Set<Drug>> supplierDrugs = new HashMap<>();
    private final CustomHeap<Drug> lowStockHeap = new CustomHeap<>(Comparator.comparingInt(Drug::getQuantity));

    public DrugStorage() {
        loadFromCSV();
    }
    
    private void loadFromCSV() {
        try {
            List<String[]> records = CSVUtils.readCSV("data/drugs.csv");
            for (String[] record : records) {
                if (record.length < 6) continue;
                
                Drug drug = new Drug(
                    record[0], record[1], Integer.parseInt(record[2]), 
                    Double.parseDouble(record[3]), LocalDate.parse(record[4]), record[5]
                );
                addDrug(drug);
            }
        } catch (IOException e) {
            System.err.println("Error loading drugs from CSV: " + e.getMessage());
        }
    }
    
    private void saveToCSV(Drug drug) {
        try {
            CSVUtils.appendToCSV("data/drugs.csv", new String[]{
                drug.getCode(),
                drug.getName(),
                String.valueOf(drug.getQuantity()),
                String.valueOf(drug.getPrice()),
                drug.getExpiry().toString(),
                drug.getSupplierId()
            });
        } catch (IOException e) {
            System.err.println("Error saving drug to CSV: " + e.getMessage());
        }
    }

    public void addDrug(Drug drug) {
        drugMap.put(drug.getCode(), drug);
        supplierDrugs.computeIfAbsent(drug.getSupplierId(), k -> new HashSet<>()).add(drug);
        lowStockHeap.insert(drug);
        saveToCSV(drug);
    }

    public Drug getDrug(String code) {
        return drugMap.get(code);
    }

    public void updateDrug(Drug drug) {
        drugMap.put(drug.getCode(), drug);
        lowStockHeap.heapify();
    }

    public void removeDrug(String code) {
        Drug drug = drugMap.remove(code);
        if (drug != null) {
            supplierDrugs.get(drug.getSupplierId()).remove(drug);
            lowStockHeap.remove(drug);
        }
    }

    public List<Drug> getAllDrugs() {
        return new ArrayList<>(drugMap.values());
    }

    public List<Drug> getDrugsBySupplier(String supplierId) {
        Set<Drug> drugs = supplierDrugs.get(supplierId);
        return drugs != null ? new ArrayList<>(drugs) : new ArrayList<>();
    }

    public List<Drug> getLowStockDrugs(int threshold) {
        List<Drug> result = new ArrayList<>();
        for (Drug drug : drugMap.values()) {
            if (drug.getQuantity() < threshold) {
                result.add(drug);
            }
        }
        return result;
    }

    public Drug getMinStockDrug() {
        return lowStockHeap.peek();
    }
}