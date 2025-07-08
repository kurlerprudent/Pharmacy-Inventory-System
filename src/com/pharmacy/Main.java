package com.pharmacy;
import java.util.List;

import com.pharmacy.storage.DrugStorage;
import com.pharmacy.ui.MenuSystem;
import com.pharmacy.logic.InventoryManager;
import com.pharmacy.logic.StockMonitoring;
import com.pharmacy.models.Drug;

public class Main {
    public static void main(String[] args) {
        System.out.println("🏥 Starting Pharmacy Inventory System...");
        
        // Initialize components
        DrugStorage drugStorage = new DrugStorage("data/drugs.csv");
        InventoryManager inventoryManager = new InventoryManager();
        StockMonitoring stockMonitoring = new StockMonitoring();
        MenuSystem menuSystem = new MenuSystem(inventoryManager);
        
        // Load initial data
         List<Drug> drugs = drugStorage.loadDrugs();
        // inventoryManager.setDrugs(drugs);

         stockMonitoring.checkDrugsWithLowStock(drugs); // This checks  drugs out of stock
        
        // Start application
        //menuSystem.showMainMenu();
        
        System.out.println("🛑 System shutdown");
    }
}