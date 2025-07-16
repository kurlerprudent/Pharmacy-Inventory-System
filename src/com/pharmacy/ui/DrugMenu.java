package com.pharmacy.ui;

import com.pharmacy.logic.InventoryManager;
import com.pharmacy.logic.SearchService;
import com.pharmacy.logic.SortService;
import com.pharmacy.models.Drug;
import com.pharmacy.storage.DrugStorage;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class DrugMenu {
    private final InventoryManager inventoryManager;
    private final SearchService searchService;
    private final SortService sortService;
    private final DrugStorage drugStorage;

    public DrugMenu(InventoryManager inventoryManager,
            SearchService searchService,
            DrugStorage drugStorage) {
        this.inventoryManager = inventoryManager;
        this.searchService = searchService;
        this.sortService = new SortService();
        this.drugStorage = drugStorage;
    }

    public void showMenu() {
        while (true) {
            ConsoleHelper.printHeader("DRUG MANAGEMENT");
            System.out.println("1. Add New Drug");
            System.out.println("2. Update Stock");
            System.out.println("3. Adjust Price");
            System.out.println("4. Search Drugs");
            System.out.println("5. View All Drugs");
            System.out.println("6. View Drugs by Expiry");
            System.out.println("7. Remove Drug");
            System.out.println("8. Back to Main Menu");

            System.out.print("Enter choice: ");

            int choice = ConsoleHelper.getIntInput();

            switch (choice) {
                case 1:
                    addDrug();
                    break;
                case 2:
                    updateStock();
                    break;
                case 3:
                    adjustPrice();
                    break;
                case 4:
                    searchDrugs();
                    break;
                case 5:
                    viewAllDrugs();
                    break;
                case 6:
                    viewByExpiry();
                    break;
                case 7:
                    removeDrug();
                    break;
                case 8:
                    return;

                default:
                    ConsoleHelper.printError("Invalid choice");
            }
        }
    }

    private void addDrug() {
        ConsoleHelper.printHeader("ADD NEW DRUG");
        System.out.print("Enter drug code: ");
        String code = ConsoleHelper.scanner.nextLine().trim();
        System.out.print("Enter drug name: ");
        String name = ConsoleHelper.scanner.nextLine().trim();
        System.out.print("Enter quantity: ");
        int quantity = ConsoleHelper.getIntInput();
        System.out.print("Enter price: ");
        double price = ConsoleHelper.getDoubleInput();
        System.out.print("Enter expiry date (YYYY-MM-DD): ");
        String expiry = ConsoleHelper.scanner.nextLine().trim();
        System.out.print("Enter supplier IDs (comma-separated): ");
        String suppliersLine = ConsoleHelper.scanner.nextLine().trim();
        List<String> suppliers = Arrays.stream(suppliersLine.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        try {
            Drug drug = new Drug(
                    code, name, quantity,
                    price, LocalDate.parse(expiry),
                    suppliers);
            inventoryManager.addDrug(drug);
            ConsoleHelper.printSuccess("Drug added successfully!");
        } catch (Exception e) {
            ConsoleHelper.printError(e.getMessage());
        }
    }

    private void updateStock() {
        ConsoleHelper.printHeader("UPDATE STOCK");

        System.out.print("Enter drug code: ");
        String code = ConsoleHelper.scanner.nextLine();

        System.out.print("Enter quantity change (+/-): ");
        int change = ConsoleHelper.getIntInput();

        try {
            inventoryManager.updateStock(code, change);
            ConsoleHelper.printSuccess("Stock updated successfully!");
        } catch (Exception e) {
            ConsoleHelper.printError(e.getMessage());
        }
    }

    private void adjustPrice() {
        ConsoleHelper.printHeader("ADJUST PRICE");

        System.out.print("Enter drug code: ");
        String code = ConsoleHelper.scanner.nextLine();

        System.out.print("Enter new price: ");
        double price = ConsoleHelper.getDoubleInput();

        try {
            inventoryManager.adjustPrice(code, price);
            ConsoleHelper.printSuccess("Price updated successfully!");
        } catch (Exception e) {
            ConsoleHelper.printError(e.getMessage());
        }
    }

    private void searchDrugs() {
        ConsoleHelper.printHeader("SEARCH DRUGS");
        System.out.println("1. Search by Name");
        System.out.println("2. Search by Supplier");
        System.out.print("Enter choice: ");

        int choice = ConsoleHelper.getIntInput();

        switch (choice) {
            case 1:
                System.out.print("Enter name to search: ");
                String name = ConsoleHelper.scanner.nextLine();
                displayResults(searchService.searchByName(name));
                break;

            case 2:
                System.out.print("Enter supplier ID: ");
                String supplierId = ConsoleHelper.scanner.nextLine();
                displayResults(searchService.searchBySupplier(supplierId));
                break;

            default:
                ConsoleHelper.printError("Invalid choice");
        }
    }

    private void viewAllDrugs() {
        ConsoleHelper.printHeader("ALL DRUGS");
        displayResults(drugStorage.getAllDrugs());
    }

    private void viewByExpiry() {
        ConsoleHelper.printHeader("DRUGS BY EXPIRY DATE");
        List<Drug> drugs = sortService.sortByExpiry(drugStorage.getAllDrugs());
        displayResults(drugs);
    }

    private void displayResults(List<Drug> drugs) {
        if (drugs.isEmpty()) {
            ConsoleHelper.printWarning("No drugs found");
            return;
        }

        ConsoleHelper.printInfo("\nID       Name                 Quantity  Price    Expiry      Supplier");
        for (Drug drug : drugs) {
            ConsoleHelper.printDrug(drug);
        }
        ConsoleHelper.printInfo("Total: " + drugs.size() + " drugs");
    }

    private void removeDrug() {
        ConsoleHelper.printHeader("REMOVE DRUG");
        System.out.print("Enter drug code to remove: ");
        String code = ConsoleHelper.scanner.nextLine().trim();

        try {
            inventoryManager.removeDrug(code);
            ConsoleHelper.printSuccess("Drug removed successfully!");
        } catch (Exception e) {
            ConsoleHelper.printError(e.getMessage());
        }
    }

}