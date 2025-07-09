package com.pharmacy;

import java.util.List;

import com.pharmacy.logic.InventoryManager;
import com.pharmacy.logic.StockMonitoring;
import com.pharmacy.models.Drug;
import com.pharmacy.storage.DrugStorage;
import com.pharmacy.ui.MenuSystem;

public class Main {
    public static void main(String[] args) {
        System.out.println("🏥 Starting Pharmacy Inventory System...");

        // Load drugs from file
        DrugStorage drugStorage = new DrugStorage("data/drugs.csv");
        List<Drug> drugs = drugStorage.loadDrugs();

        // Initialize inventory with loaded drugs
        InventoryManager inventoryManager = new InventoryManager(drugs);

        // Check for low stock drugs
        //StockMonitoring stockMonitoring = new StockMonitoring();
        //stockMonitoring.checkDrugsWithLowStock(drugs);

        // Start menu system
        MenuSystem menuSystem = new MenuSystem(inventoryManager);
        menuSystem.showMainMenu();

        System.out.println("🛑 System shutdown");
    }
}
