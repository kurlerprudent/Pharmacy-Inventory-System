package com.pharmacy.logic;

import com.pharmacy.models.Drug;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manages drug inventory operations
 */
public class InventoryManager {
    private List<Drug> drugs;

    // Constructor to initialize list
    public InventoryManager() {
        this.drugs = new ArrayList<>();
    }

    /**
     * Adds a drug to the inventory
     */
    public void addDrug(Drug drug) {
        // TODO: Add validation logic if needed
        drugs.add(drug);
    }

    /**
     * Removes a drug by its code
     */
    public void removeDrug(String drugCode) {
        Iterator<Drug> iterator = drugs.iterator();
        while (iterator.hasNext()) {
            Drug drug = iterator.next();
            if (drug.getCode().equalsIgnoreCase(drugCode)) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Updates the quantity or price of a drug
     */
    public void updateDrug(String code, int newQuantity, double newPrice) {
        for (Drug drug : drugs) {
            if (drug.getCode().equalsIgnoreCase(code)) {
                drug.setQuantity(newQuantity);
                drug.setPrice(newPrice);
                break;
            }
        }
    }

    /**
     * Gets a single drug by its code
     */
    public Drug getDrug(String code) {
        for (Drug drug : drugs) {
            if (drug.getCode().equalsIgnoreCase(code)) {
                return drug;
            }
        }
        return null;
    }

    /**
     * ✅ Returns all drugs (used by MenuSystem)
     */
    public List<Drug> getAllDrugs() {
        return new ArrayList<>(drugs); // Return a copy for safety
    }
}
