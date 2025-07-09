package com.pharmacy.ui;

import com.pharmacy.logic.InventoryManager;
import com.pharmacy.logic.SalesLogManager;
import com.pharmacy.models.Transaction;
import com.pharmacy.models.Drug;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Main menu controller
 */
public class MenuSystem {
    private final InventoryManager inventoryManager;
    private final SalesLogManager salesLogManager;
    private final Scanner scanner;

    public MenuSystem(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
        this.salesLogManager = new SalesLogManager();
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() {
        boolean running = true;
        while (running) {
            ConsoleHelper.printHeader("MAIN MENU");
            System.out.println("1. Drug Management");
            System.out.println("2. Supplier Management");
            System.out.println("3. Process Sale");
            System.out.println("4. Reports");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    showDrugMenu();
                    break;
                case "2":
                    // showSupplierMenu(); // Placeholder for future implementation
                    System.out.println("Supplier Management not implemented yet.");
                    break;
                case "3":
                    handleRecordSale();
                    break;
                case "4":
                    showReportsMenu();
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void showDrugMenu() {
        ConsoleHelper.printHeader("DRUG MANAGEMENT");
        System.out.println("1. Add Drug");
        System.out.println("2. Remove Drug");
        System.out.println("3. Update Drug");
        System.out.println("4. List Drugs");
        System.out.println("5. Back to Main Menu");
        System.out.print("Select an option: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                ConsoleHelper.handleAddDrug(scanner, inventoryManager);
                break;
            case "2":
                ConsoleHelper.handleRemoveDrug(scanner, inventoryManager);
                break;
            case "3":
                ConsoleHelper.handleUpdateDrug(scanner, inventoryManager);
                break;
            case "4":
                inventoryManager.listDrugs();
                break;
            case "5":
                return;
            default:
                System.out.println("Invalid input. Returning to main menu.");
        }
    }

    private void handleRecordSale() {
        ConsoleHelper.printHeader("PROCESS SALE");

        System.out.print("Drug Code: ");
        String code = scanner.nextLine().trim();

        Drug drug = inventoryManager.getDrug(code);  // ✅ Uses your method
        if (drug == null) {
            ConsoleHelper.printError("❌ Drug not found.");
            return;
        }

        System.out.print("Quantity: ");
        int qty;
        try {
            qty = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            ConsoleHelper.printError("❌ Invalid quantity.");
            return;
        }

        if (qty > drug.getQuantity()) {
            ConsoleHelper.printError("❌ Not enough stock.");
            return;
        }

        double totalCost = qty * drug.getPrice();
        System.out.printf("💰 Total Cost: %.2f%n", totalCost);

        System.out.print("Buyer ID: ");
        String buyer = scanner.nextLine().trim();

        // ✅ Update stock
        drug.setQuantity(drug.getQuantity() - qty);

        // ✅ Record the transaction
        Transaction tx = new Transaction(code, qty, totalCost, buyer);
        salesLogManager.recordSale(tx);

        ConsoleHelper.printSuccess("✅ Sale recorded and stock updated.");
    }


    private void showReportsMenu() {
        ConsoleHelper.printHeader("REPORTS");
        System.out.println("1. View Sales Report");
        System.out.println("2. Back to Main Menu");
        System.out.print("Select an option: ");
        String input = scanner.nextLine();

        switch (input) {
            case "1":
                handleViewSalesReport();
                break;
            case "2":
                return;
            default:
                System.out.println("Invalid input.");
        }
    }

    private void handleViewSalesReport() {
        try {
            System.out.print("Start Date (yyyy-MM-dd): ");
            LocalDate start = LocalDate.parse(scanner.nextLine().trim());

            System.out.print("End Date (yyyy-MM-dd): ");
            LocalDate end = LocalDate.parse(scanner.nextLine().trim());

            salesLogManager.viewSalesReport(start, end);
        } catch (Exception e) {
            System.out.println("❌ Invalid date format.");
        }
    }
}
