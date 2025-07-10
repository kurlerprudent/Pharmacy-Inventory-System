package com.pharmacy.ui;

import com.pharmacy.logic.StockMonitor;
import com.pharmacy.models.Drug;
import com.pharmacy.models.Transaction;
import com.pharmacy.storage.DrugStorage;
import com.pharmacy.storage.TransactionStorage;
import java.util.List;

public class ReportMenu {
    private final StockMonitor stockMonitor;
    private final DrugStorage drugStorage;
    private final TransactionStorage transactionStorage;

    public ReportMenu(StockMonitor stockMonitor, 
                     DrugStorage drugStorage,
                     TransactionStorage transactionStorage) {
        this.stockMonitor = stockMonitor;
        this.drugStorage = drugStorage;
        this.transactionStorage = transactionStorage;
    }

    public void showMenu() {
        while (true) {
            ConsoleHelper.printHeader("REPORTING");
            System.out.println("1. Low Stock Report");
            System.out.println("2. Expiry Report");
            System.out.println("3. Supplier Report");
            System.out.println("4. Sales Report");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");
            
            int choice = ConsoleHelper.getIntInput();
            
            switch (choice) {
                case 1: lowStockReport(); break;
                case 2: expiryReport(); break;
                case 3: supplierReport(); break;
                case 4: salesReport(); break;
                case 5: return;
                default: ConsoleHelper.printError("Invalid choice");
            }
        }
    }

      private void salesReport() {
        ConsoleHelper.printHeader("SALES REPORT");
        List<Transaction> transactions = transactionStorage.getAllTransactions();
        
        if (transactions.isEmpty()) {
            ConsoleHelper.printWarning("No sales data available");
            return;
        }
        
        double totalRevenue = 0;
        ConsoleHelper.printInfo("Recent Sales:");
        ConsoleHelper.printInfo("ID       Drug            Customer    Qty  Date                Revenue");
        
        for (Transaction t : transactions) {
            Drug drug = drugStorage.getDrug(t.getDrugCode());
            if (drug == null) continue;
            
            double revenue = drug.getPrice() * t.getQuantity();
            totalRevenue += revenue;
            
            System.out.printf("%-8s %-15s %-10s %-5d %s $%.2f\n",
                t.getId(),
                drug.getName(),
                t.getCustomerId(),
                t.getQuantity(),
                t.getDate().toString().replace("T", " "),
                revenue
            );
        }
        
        ConsoleHelper.printSuccess("Total Revenue: $" + totalRevenue);
    }


    private void lowStockReport() {
        ConsoleHelper.printHeader("LOW STOCK REPORT");
        System.out.print("Enter threshold: ");
        int threshold = ConsoleHelper.getIntInput();
        
        List<Drug> lowStock = stockMonitor.getLowStockDrugs(threshold);
        displayResults("Low Stock Items (Threshold: " + threshold + ")", lowStock);
    }

    private void expiryReport() {
        ConsoleHelper.printHeader("EXPIRY REPORT");
        System.out.print("Enter days threshold: ");
        int days = ConsoleHelper.getIntInput();
        
        List<Drug> expiring = stockMonitor.checkExpiringDrugs(days);
        displayResults("Expiring Soon (Within " + days + " days)", expiring);
    }

    private void supplierReport() {
        ConsoleHelper.printHeader("SUPPLIER REPORT");
        System.out.print("Enter supplier ID: ");
        String supplierId = ConsoleHelper.scanner.nextLine();
        
        List<Drug> supplierDrugs = drugStorage.getDrugsBySupplier(supplierId);
        displayResults("Drugs from Supplier " + supplierId, supplierDrugs);
    }

    private void displayResults(String title, List<Drug> drugs) {
        if (drugs.isEmpty()) {
            ConsoleHelper.printSuccess("No matching items found");
            return;
        }
        
        ConsoleHelper.printHeader(title);
        ConsoleHelper.printInfo("ID       Name                 Quantity  Price    Expiry      Supplier");
        for (Drug drug : drugs) {
            ConsoleHelper.printDrug(drug);
        }
        ConsoleHelper.printInfo("Total: " + drugs.size() + " items");
    }}
