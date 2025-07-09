package com.pharmacy.logic;

import com.pharmacy.models.Drug;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manages drug inventory operations
 */
public class InventoryManager {
    private List<Drug> drugs;

    public InventoryManager() {
        this.drugs = new ArrayList<>();
    }

    // Add a new drug to inventory
    public boolean addDrug(Drug drug) {
        if (drug.getQuantity() < 0 || drug.getPrice() < 0) {
            System.out.println("❌ Quantity and price must be non-negative.");
            return false;
        }

        if (drug.isExpired()) {
            System.out.println("⚠️ Cannot add expired drug.");
            return false;
        }

        for (Drug d : drugs) {
            if (d.getCode().equalsIgnoreCase(drug.getCode())) {
                System.out.println("❌ Drug with this code already exists.");
                return false;
            }
        }

        drugs.add(drug);
        System.out.println("✅ Drug added successfully.");
        return true;
    }

    // Remove a drug by code
    public boolean removeDrug(String drugCode) {
        Iterator<Drug> iterator = drugs.iterator();
        while (iterator.hasNext()) {
            Drug d = iterator.next();
            if (d.getCode().equalsIgnoreCase(drugCode)) {
                iterator.remove();
                System.out.println("🗑️ Drug removed: " + d.getName());
                return true;
            }
        }
        System.out.println("❌ Drug code not found.");
        return false;
    }

    // Update an existing drug
    public boolean updateDrug(String drugCode, Drug updatedDrug) {
        for (int i = 0; i < drugs.size(); i++) {
            Drug d = drugs.get(i);
            if (d.getCode().equalsIgnoreCase(drugCode)) {
                drugs.set(i, updatedDrug);
                System.out.println("✅ Drug updated successfully.");
                return true;
            }
        }
        System.out.println("❌ Drug not found.");
        return false;
    }

    // List all drugs
    public void listDrugs() {
        if (drugs.isEmpty()) {
            System.out.println("📦 No drugs available in inventory.");
            return;
        }

        System.out.println("\n--- Drug Inventory ---");
        for (Drug d : drugs) {
            System.out.println(d);
        }
    }

    // Find a drug by code (for UI or transactions)
    public Drug getDrugByCode(String code) {
        for (Drug d : drugs) {
            if (d.getCode().equalsIgnoreCase(code)) {
                return d;
            }
        }
        return null;
    }

    // Expose list (for saving)
    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }
}
