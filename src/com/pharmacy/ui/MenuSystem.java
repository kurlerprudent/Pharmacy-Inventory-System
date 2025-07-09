package com.pharmacy.ui;

import com.pharmacy.logic.InventoryManager;
import com.pharmacy.logic.SearchService;
import com.pharmacy.logic.SortService;
import com.pharmacy.models.Drug;
import java.util.List;
import java.util.Scanner;

/**
 * Main menu controller
 */
public class MenuSystem {
    private final InventoryManager inventoryManager;
    private final SearchService searchService = new SearchService();
    private final SortService sortService = new SortService();
    private final Scanner scanner = new Scanner(System.in);

    public MenuSystem(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public void showMainMenu() {
        while (true) {
            ConsoleHelper.printHeader("MAIN MENU");
            System.out.println("1. Drug Management");
            System.out.println("2. Supplier Management");
            System.out.println("3. Process Sale");
            System.out.println("4. Reports");
            System.out.println("5. Search & Sort Drugs");
            System.out.println("6. Exit");

            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // TODO: Drug Management Menu
                    break;
                case "2":
                    // TODO: Supplier Management Menu
                    break;
                case "3":
                    // TODO: Sale Processing
                    break;
                case "4":
                    // TODO: Report Generation
                    break;
                case "5":
                    searchAndSortMenu();
                    break;
                case "6":
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void searchAndSortMenu() {
        List<Drug> drugs = inventoryManager.getAllDrugs(); // 💥 Make sure this method exists!

        if (drugs.isEmpty()) {
            System.out.println("No drugs in inventory to search or sort.");
            return;
        }

        while (true) {
            ConsoleHelper.printHeader("SEARCH & SORT MENU");
            System.out.println("1. Search by Drug Name");
            System.out.println("2. Search by Drug Code");
            System.out.println("3. Search by Supplier ID");
            System.out.println("4. Sort by Name (A-Z)");
            System.out.println("5. Sort by Price (Low to High)");
            System.out.println("6. Back to Main Menu");

            System.out.print("\nEnter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter drug name: ");
                    String name = scanner.nextLine();
                    List<Drug> byName = searchService.linearSearchByName(drugs, name);
                    printDrugList(byName);
                    break;

                case "2":
                    System.out.print("Enter drug code: ");
                    String code = scanner.nextLine();
                    Drug found = searchService.binarySearchByCode(drugs, code);
                    if (found != null) {
                        System.out.println("\nDrug Found:\n" + found);
                    } else {
                        System.out.println("Drug not found.");
                    }
                    break;

                case "3":
                    System.out.print("Enter supplier ID: ");
                    String supplier = scanner.nextLine();
                    List<Drug> bySupplier = searchService.linearSearchBySupplier(drugs, supplier);
                    printDrugList(bySupplier);
                    break;

                case "4":
                    List<Drug> sortedByName = sortService.insertionSortByName(drugs);
                    System.out.println("\nDrugs Sorted by Name (A-Z):");
                    printDrugList(sortedByName);
                    break;

                case "5":
                    List<Drug> sortedByPrice = sortService.mergeSortByPrice(drugs);
                    System.out.println("\nDrugs Sorted by Price (Low to High):");
                    printDrugList(sortedByPrice);
                    break;

                case "6":
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void printDrugList(List<Drug> drugs) {
        if (drugs == null || drugs.isEmpty()) {
            System.out.println("No results found.");
            return;
        }

        for (Drug drug : drugs) {
            System.out.println("\n------------------------");
            System.out.println(drug);
        }
    }
}
