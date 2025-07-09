package com.pharmacy.logic;

import com.pharmacy.models.Drug;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Manages drug inventory operations
 */
public class InventoryManager {
    private List<Drug> drugs;

    public InventoryManager(List<Drug> drugs) {
        this.drugs = drugs;
    }

    public void addDrug(Drug drug) {
        // Validate inputs
        if (drug.getQuantity() < 0 || drug.getPrice() < 0) {
            System.out.println("❌ Invalid quantity or price.");
            return;
        }

        if (getDrug(drug.getCode()) != null) {
            System.out.println("❌ Drug with code " + drug.getCode() + " already exists.");
            return;
        }

        drugs.add(drug);
        System.out.println("✅ Drug added successfully.");
    }

    public void removeDrug(String drugCode) {
        Iterator<Drug> iterator = drugs.iterator();
        while (iterator.hasNext()) {
            Drug d = iterator.next();
            if (d.getCode().equalsIgnoreCase(drugCode)) {
                iterator.remove();
                System.out.println("✅ Drug removed successfully.");
                return;
            }
        }
        System.out.println("❌ Drug not found.");
    }

    public void updateDrug(String drugCode, Scanner scanner) {
        Drug found = getDrug(drugCode);
        if (found == null) {
            System.out.println("❌ Drug not found.");
            return;
        }

        System.out.println("\nLeave field empty to keep current value.");

        System.out.print("Enter new name (" + found.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isBlank()) found.setName(name);

        System.out.print("Enter new price (" + found.getPrice() + "): ");
        String priceStr = scanner.nextLine();
        if (!priceStr.isBlank()) found.setPrice(Double.parseDouble(priceStr));

        System.out.print("Enter new quantity (" + found.getQuantity() + "): ");
        String qtyStr = scanner.nextLine();
        if (!qtyStr.isBlank()) found.setQuantity(Integer.parseInt(qtyStr));

        System.out.print("Enter new expiry date (" + found.getExpiryDate() + "): ");
        String expStr = scanner.nextLine();
        if (!expStr.isBlank()) found.setExpiryDate(java.time.LocalDate.parse(expStr));

        System.out.println("✅ Drug updated successfully.");
    }

    public Drug getDrug(String drugCode) {
        for (Drug d : drugs) {
            if (d.getCode().equalsIgnoreCase(drugCode)) {
                return d;
            }
        }
        return null;
    }

    public void listDrugs() {
        if (drugs == null || drugs.isEmpty()) {
            System.out.println("No drugs in inventory.");
            return;
        }

        System.out.println("\n--- Drug Inventory ---");
        for (Drug d : drugs) {
            System.out.println(d);
        }
    }
}
