package com.pharmacy.ui;

import com.pharmacy.logic.InventoryManager;
import com.pharmacy.models.Drug;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Handles console output formatting and colors
 */
public class ConsoleHelper {
    // ANSI color codes
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";

    public static void printSuccess(String message) {
        System.out.println(GREEN + message + RESET);
    }

    public static void printError(String message) {
        System.out.println(RED + message + RESET);
    }

    public static void printHeader(String title) {
        System.out.println(YELLOW + "=== " + title + " ===" + RESET);
    }

    // === 🔽 Drug Management Methods 🔽 ===

    public static void handleAddDrug(Scanner scanner, InventoryManager inventoryManager) {
        printHeader("Add New Drug");

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
        LocalDate expiry = LocalDate.parse(scanner.nextLine().trim());

        Drug newDrug = new Drug(code, name, supplierIds, quantity, price, expiry);
        inventoryManager.addDrug(newDrug);

        printSuccess("✅ Drug added successfully.");
    }

    public static void handleRemoveDrug(Scanner scanner, InventoryManager inventoryManager) {
        printHeader("Remove Drug");
        System.out.print("Enter Drug Code to Remove: ");
        String code = scanner.nextLine();
        inventoryManager.removeDrug(code);
        printSuccess("✅ Drug removed (if it existed).");
    }

    public static void handleUpdateDrug(Scanner scanner, InventoryManager inventoryManager) {
        printHeader("Update Drug");
        System.out.print("Enter Drug Code to Update: ");
        String code = scanner.nextLine();

        Drug drug = inventoryManager.getDrug(code);
        if (drug == null) {
            printError("❌ Drug not found.");
            return;
        }

        System.out.println("Leave input blank to keep current value.");

        System.out.print("Name (" + drug.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isBlank()) drug.setName(name);

        System.out.print("Quantity (" + drug.getQuantity() + "): ");
        String qty = scanner.nextLine();
        if (!qty.isBlank()) drug.setQuantity(Integer.parseInt(qty));

        System.out.print("Price (" + drug.getPrice() + "): ");
        String price = scanner.nextLine();
        if (!price.isBlank()) drug.setPrice(Double.parseDouble(price));

        System.out.print("Expiry (" + drug.getExpiryDate() + "): ");
        String expiry = scanner.nextLine();
        if (!expiry.isBlank()) drug.setExpiryDate(LocalDate.parse(expiry.trim()));

        printSuccess("✅ Drug updated successfully.");
    }

    // TODO: Add input validation helpers
}
