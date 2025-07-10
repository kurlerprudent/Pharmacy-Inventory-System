package com.pharmacy.logic;

import com.pharmacy.models.Drug;
import com.pharmacy.storage.DrugStorage;
import com.pharmacy.utils.ValidationUtils;

public class InventoryManager {
    private final DrugStorage drugStorage;

    public InventoryManager(DrugStorage drugStorage) {
        this.drugStorage = drugStorage;
    }

    public void addDrug(Drug drug) {
        if (!ValidationUtils.isValidDrugCode(drug.getCode())) {
            throw new IllegalArgumentException("Invalid drug code format");
        }
        if (drugStorage.getDrug(drug.getCode()) != null) {
            throw new IllegalArgumentException("Drug code already exists");
        }
        drugStorage.addDrug(drug);
    }

    public void updateDrug(Drug updatedDrug) {
        Drug existing = drugStorage.getDrug(updatedDrug.getCode());
        if (existing == null) {
            throw new IllegalArgumentException("Drug not found");
        }
        drugStorage.updateDrug(updatedDrug);
    }

    public void updateStock(String drugCode, int quantityChange) {
        Drug drug = drugStorage.getDrug(drugCode);
        if (drug == null) throw new IllegalArgumentException("Drug not found");
        
        int newQuantity = drug.getQuantity() + quantityChange;
        if (newQuantity < 0) throw new IllegalArgumentException("Insufficient stock");
        
        drug.setQuantity(newQuantity);
        drugStorage.updateDrug(drug);
    }

    public void adjustPrice(String drugCode, double newPrice) {
        if (newPrice <= 0) throw new IllegalArgumentException("Price must be positive");
        
        Drug drug = drugStorage.getDrug(drugCode);
        if (drug == null) throw new IllegalArgumentException("Drug not found");
        
        drug.setPrice(newPrice);
        drugStorage.updateDrug(drug);
    }
}