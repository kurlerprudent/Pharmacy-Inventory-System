package com.pharmacy.ui;

import com.pharmacy.logic.InventoryManager;
import com.pharmacy.models.Drug;
import com.pharmacy.storage.DrugStorage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Main menu controller
 */
public class MenuSystem {
    private final InventoryManager inventoryManager;
    private final DrugStorage drugStorage;
    private final Scanner scanner = new Scanner(System.in);

    public MenuSystem(InventoryManager inventoryManager, DrugStorage drugStorage) {
        this.inventoryManager = inventoryManager;
        this.drugStorage = drugStorage;
    }

    public void showMainMenu() {
        while (true) {
            ConsoleHelper.printHeader("MAIN MENU");
            System.out.println("1. Drug Management");
            System.out.println("2. Supplier Management");
            System.out.println("3. Process Sale");
            System.out.println("4. Reports");
            System.out.println("5. Exit");

            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showDrugMenu();
                    break;
                case "5":
                    System.out.println("Exiting system...");
                    drugStorage.saveDrugs(inventoryManager.getDrugs());
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void showDrugMenu() {
        while (true) {
            ConsoleHelper.printHeader("DRUG MANAGEMENT");
            System.out.println("1. Add Drug");
            System.out.println("2. Remove Drug");
            System.out.println("3. Update Drug");
            System.out.println("4. List Drugs");
            System.out.println("5. Back");

            System.out.print("Select an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    handleAddDrug();
                    break;
                case "2":
                    handleRemoveDrug();
                    break;
                case "3":
                    handleUpdateDrug();
                    break;
                case "4":
                    inventoryManager.listDrugs();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void handleAddDrug() {
        System.out.println("\nEnter Drug Details:");
        System.out.print("Code: ");
        String code = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Supplier IDs (comma-separated): ");
        List<String> supplierIds = Arrays.asList(scanner.nextLine().split(","));
        System.out.print("Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Expiry Date (YYYY-MM-DD): ");
        LocalDate expiryDate = LocalDate.parse(scanner.nextLine().trim());

        Drug newDrug = new Drug(code, name, supplierIds, quantity, price, expiryDate);
        inventoryManager.addDrug(newDrug);
        System.out.println("✅ Drug added.");
    }

    private void handleRemoveDrug() {
        System.out.print("Enter drug code to remove: ");
        String code = scanner.nextLine();
        inventoryManager.removeDrug(code);
        System.out.println("✅ Drug removed (if code existed).");
    }

    private void handleUpdateDrug() {
        System.out.print("Enter drug code to update: ");
        String code = scanner.nextLine();
        Drug existing = inventoryManager.getDrugByCode(code);

        if (existing == null) {
            System.out.println("❌ Drug not found.");
            return;
        }

        System.out.println("Leave field blank to keep existing.");

        System.out.print("New name (" + existing.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isBlank()) existing.setName(name);

        System.out.print("New quantity (" + existing.getQuantity() + "): ");
        String qtyStr = scanner.nextLine();
        if (!qtyStr.isBlank()) existing.setQuantity(Integer.parseInt(qtyStr));

        System.out.print("New price (" + existing.getPrice() + "): ");
        String priceStr = scanner.nextLine();
        if (!priceStr.isBlank()) existing.setPrice(Double.parseDouble(priceStr));

        System.out.print("New expiry (" + existing.getExpiryDate() + "): ");
        String expStr = scanner.nextLine();
        if (!expStr.isBlank()) existing.setExpiryDate(LocalDate.parse(expStr.trim()));


        System.out.print("New suppliers (comma-separated): ");
        String supplierStr = scanner.nextLine();
        if (!supplierStr.isBlank()) {
            existing.setSupplierIds(Arrays.asList(supplierStr.split(",")));
        }

        inventoryManager.updateDrug(code, existing);
        System.out.println("✅ Drug updated.");
    }
}
