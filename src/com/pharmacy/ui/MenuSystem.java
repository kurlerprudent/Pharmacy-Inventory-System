package com.pharmacy.ui;

import com.pharmacy.logic.*;
import com.pharmacy.storage.*;

public class MenuSystem {
    private final DrugStorage drugStorage = new DrugStorage();
    private final SupplierStorage supplierStorage = new SupplierStorage();
    private final CustomerStorage customerStorage = new CustomerStorage();
    private final TransactionStorage transactionStorage = new TransactionStorage();
    
    private final InventoryManager inventoryManager = new InventoryManager(drugStorage);
    private final SearchService searchService = new SearchService(drugStorage);
    private final StockMonitor stockMonitor = new StockMonitor(drugStorage);
    
    private final DrugMenu drugMenu = new DrugMenu(inventoryManager, searchService, drugStorage);
    private final ReportMenu reportMenu = new ReportMenu(stockMonitor, drugStorage, transactionStorage);
    private final SalesMenu salesMenu = new SalesMenu(
        transactionStorage, drugStorage, customerStorage, inventoryManager
    );
    
    public void start() {
        mainMenu();
    }
    
    private void mainMenu() {
        while (true) {
            ConsoleHelper.printHeader("PHARMACY INVENTORY SYSTEM");
            System.out.println("1. Drug Management");
            System.out.println("2. Sales");
            System.out.println("3. Reports");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            
            int choice = ConsoleHelper.getIntInput();
            
            switch (choice) {
                case 1: drugMenu.showMenu(); break;
                case 2: salesMenu.showMenu(); break;
                case 3: reportMenu.showMenu(); break;
                case 4: 
                    ConsoleHelper.printSuccess("Exiting system. Goodbye!");
                    System.exit(0);
                default: ConsoleHelper.printError("Invalid choice");
            }
        }
    }
}