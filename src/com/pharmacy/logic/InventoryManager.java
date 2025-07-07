package com.pharmacy.logic;

import com.pharmacy.models.Drug;
import java.util.List;

/**
 * Manages drug inventory operations
 */
public class InventoryManager {
    private List<Drug> drugs;
    
    public void addDrug(Drug drug) {
        // TODO: Validate inputs
        // - Check for negative quantity
        // - Check duplicate drug code
        // - Validate expiry date
        drugs.add(drug);
    }
    
    public void removeDrug(String drugCode) {
        // TODO: Implement removal logic
    }
    
    // TODO: Add updateDrug, getDrug methods
}