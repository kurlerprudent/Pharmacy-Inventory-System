package com.pharmacy.ui;

import com.pharmacy.logic.InventoryManager;

/**
 * Main menu controller
 */
public class MenuSystem {
    private final InventoryManager inventoryManager;
    
    public MenuSystem(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }
    
    public void showMainMenu() {
        ConsoleHelper.printHeader("MAIN MENU");
        System.out.println("1. Drug Management");
        System.out.println("2. Supplier Management");
        System.out.println("3. Process Sale");
        System.out.println("4. Reports");
        System.out.println("5. Exit");
        
        // TODO: Handle user input and navigation
    }
    
    // TODO: Add navigation methods
}