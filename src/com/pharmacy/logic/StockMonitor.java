package com.pharmacy.logic;

import com.pharmacy.models.Drug;
import com.pharmacy.storage.DrugStorage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StockMonitor {
    private final DrugStorage drugStorage;

    public StockMonitor(DrugStorage drugStorage) {
        this.drugStorage = drugStorage;
    }

    public List<Drug> checkExpiringDrugs(int days) {
        List<Drug> expiring = new ArrayList<>();
        LocalDate threshold = LocalDate.now().plusDays(days);
        
        for (Drug drug : drugStorage.getAllDrugs()) {
            if (drug.getExpiry().isBefore(threshold) || drug.getExpiry().isEqual(threshold)) {
                expiring.add(drug);
            }
        }
        return expiring;
    }

    public List<Drug> getLowStockDrugs(int threshold) {
        return drugStorage.getLowStockDrugs(threshold);
    }
    
    public Drug getMinStockDrug() {
        return drugStorage.getMinStockDrug();
    }
    
    public void generateStockAlert(int threshold) {
        for (Drug drug : drugStorage.getLowStockDrugs(threshold)) {
            System.out.println("ALERT: Low stock for " + drug.getName() + 
                              " (Current: " + drug.getQuantity() + ")");
        }
    }
}