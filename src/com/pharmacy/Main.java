package com.pharmacy;

import com.pharmacy.storage.DrugStorage;
import com.pharmacy.ui.MenuSystem;
import com.pharmacy.logic.InventoryManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("🏥 Starting Pharmacy Inventory System...");
        
        // Initialize components
        DrugStorage drugStorage = new DrugStorage("data/drugs.csv");
        InventoryManager inventoryManager = new InventoryManager();
        MenuSystem menuSystem = new MenuSystem(inventoryManager);
        
        // Load initial data
        // List<Drug> drugs = drugStorage.loadDrugs();
        // inventoryManager.setDrugs(drugs);
        
        // Start application
        menuSystem.showMainMenu();
        
        System.out.println("🛑 System shutdown");
    }
}