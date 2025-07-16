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
                if (record.length < 6)
                    continue;

                // Fix issue: remove quotes and split supplier IDs
                String rawSupplierData = record[5].replace("\"", "").trim();
                List<String> suppliers = Arrays.asList(rawSupplierData.split(";"));

                Drug drug = new Drug(
                        record[0], // code
                        record[1], // name
                        Integer.parseInt(record[2]), // quantity
                        Double.parseDouble(record[3]), // price
                        LocalDate.parse(record[4]), // expiry
                        suppliers // supplier IDs
                );

                addDrugInMemory(drug);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading drugs from CSV: " + e.getMessage());
        }
    }

    private void addDrugInMemory(Drug drug) {
        drugMap.put(drug.getCode(), drug);

        for (String supId : drug.getSupplierIds()) {
            supplierDrugs
                    .computeIfAbsent(supId.trim(), k -> new HashSet<>())
                    .add(drug);
        }

        lowStockHeap.insert(drug);
    }

    private void saveToCSV(Drug drug) {
        try {
            String[] parts = drug.toString().split(",", 6);
            CSVUtils.appendToCSV("data/drugs.csv", parts);
        } catch (IOException e) {
            System.err.println("Error saving drug to CSV: " + e.getMessage());
        }
    }

    public void addDrug(Drug drug) {
        addDrugInMemory(drug);
        saveToCSV(drug);
    }

    public Drug getDrug(String code) {
        return drugMap.get(code);
    }

    public void updateDrug(Drug drug) {
        Drug old = drugMap.get(drug.getCode());
        if (old != null) {
            for (String sup : old.getSupplierIds()) {
                Set<Drug> set = supplierDrugs.get(sup);
                if (set != null)
                    set.remove(old);
            }
        }

        addDrugInMemory(drug);
        lowStockHeap.heapify();
        // Reminder: Update persistence requires full CSV rewrite.
    }

    public void removeDrug(String code) {
        Drug drug = drugMap.remove(code);
        if (drug != null) {
            for (String sup : drug.getSupplierIds()) {
                Set<Drug> set = supplierDrugs.get(sup);
                if (set != null)
                    set.remove(drug);
            }
            lowStockHeap.remove(drug);
            // Reminder: You need to implement CSV rewriting if persistence matters here.
        }
    }

    public List<Drug> getAllDrugs() {
        return new ArrayList<>(drugMap.values());
    }

    public List<Drug> getDrugsBySupplier(String supplierId) {
        Set<Drug> set = supplierDrugs.get(supplierId);
        return set == null ? Collections.emptyList() : new ArrayList<>(set);
    }

    public List<Drug> getLowStockDrugs(int threshold) {
        List<Drug> result = new ArrayList<>();
        for (Drug d : drugMap.values()) {
            if (d.getQuantity() < threshold)
                result.add(d);
        }
        return result;
    }

    public Drug getMinStockDrug() {
        return lowStockHeap.peek();
    }
}
