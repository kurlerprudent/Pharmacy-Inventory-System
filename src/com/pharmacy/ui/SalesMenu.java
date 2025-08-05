package com.pharmacy.ui;

import com.pharmacy.logic.InventoryManager;
import com.pharmacy.logic.PurchaseHistoryService;
import com.pharmacy.models.*;
import com.pharmacy.storage.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SalesMenu {
    private final TransactionStorage transactionStorage;
    private final DrugStorage drugStorage;
    private final CustomerStorage customerStorage;
    private final InventoryManager inventoryManager;
    private final PurchaseHistoryService purchaseHistoryService;
    private int transactionCounter = 1;

    public SalesMenu(TransactionStorage transactionStorage,
            DrugStorage drugStorage,
            CustomerStorage customerStorage,
            InventoryManager inventoryManager) {
        this.transactionStorage = transactionStorage;
        this.drugStorage = drugStorage;
        this.customerStorage = customerStorage;
        this.inventoryManager = inventoryManager;
        this.purchaseHistoryService = new PurchaseHistoryService(transactionStorage);
    }

    public void showMenu() {
        while (true) {
            ConsoleHelper.printHeader("SALES MANAGEMENT");
            System.out.println("1. Record New Sale");
            System.out.println("2. View Sales History");
            System.out.println("3. Sales Report by Period");
            System.out.println("4. Back to Main Menu"); 
            System.out.print("Enter choice: ");

            int choice = ConsoleHelper.getIntInput();

            switch (choice) {
                case 1:
                    recordSale();
                    break;
                case 2:
                    viewSalesHistory();
                    break;
                case 3:
                    salesReportByPeriod();
                    break; 
                case 4:
                    return;
                default:
                    ConsoleHelper.printError("Invalid choice");
            }
        }
    }

    private void recordSale() {
        ConsoleHelper.printHeader("RECORD NEW SALE");

        // Get drug
        System.out.print("Enter drug code: ");
        String drugCode = ConsoleHelper.scanner.nextLine().trim();
        Drug drug = drugStorage.getDrug(drugCode);
        if (drug == null) {
            ConsoleHelper.printError("Drug not found");
            return;
        }

        // Get customer
        System.out.print("Enter customer ID: ");
        String customerId = ConsoleHelper.scanner.nextLine().trim();
        Customer customer = customerStorage.getCustomer(customerId);
        if (customer == null) {
            ConsoleHelper.printError("Customer not found");
            return;
        }

        // Get quantity
        System.out.print("Enter quantity: ");
        int quantity = ConsoleHelper.getIntInput();

        // Validate quantity
        if (quantity <= 0) {
            ConsoleHelper.printError("Quantity must be positive");
            return;
        }

        if (drug.getQuantity() < quantity) {
            ConsoleHelper.printError("Insufficient stock. Available: " + drug.getQuantity());
            return;
        }

        try {
            // Create transaction
            String transactionId = "T" + String.format("%03d", transactionCounter++);
            Transaction transaction = new Transaction(transactionId, drugCode, customerId, quantity, 0.00);
            transaction.setDate(LocalDateTime.now());

            // Record transaction
            transactionStorage.addTransaction(transaction);

            // Update inventory
            inventoryManager.updateStock(drugCode, -quantity);

            ConsoleHelper.printSuccess("Sale recorded successfully!");
            ConsoleHelper.printInfo("Transaction ID: " + transactionId);
            ConsoleHelper.printInfo("Total: $" + (drug.getPrice() * quantity));
        } catch (Exception e) {
            ConsoleHelper.printError("Error recording sale: " + e.getMessage());
        }
    }

    private void viewSalesHistory() {
        ConsoleHelper.printHeader("SALES HISTORY");

        List<Transaction> transactions = transactionStorage.getAllTransactions();
        if (transactions.isEmpty()) {
            ConsoleHelper.printWarning("No sales history found");
            return;
        }

        ConsoleHelper.printInfo(String.format("%-8s %-10s %-12s %-8s %-20s",
                "ID", "Drug", "Customer", "Qty", "Date"));

        for (Transaction t : transactions) {
            Drug drug = drugStorage.getDrug(t.getDrugCode());
            Customer customer = customerStorage.getCustomer(t.getCustomerId());

            String drugName = drug != null ? drug.getName() : "Unknown";
            String customerName = customer != null ? customer.getName() : "Unknown";

            System.out.printf("%-8s %-10s %-12s %-8d %s\n",
                    t.getId(),
                    drugName,
                    customerName,
                    t.getQuantity(),
                    t.getDate());
        }
    }

    private void salesReportByPeriod() {
        ConsoleHelper.printHeader("SALES REPORT BY PERIOD");
        ConsoleHelper.printInfo("Enter start date & time (YYYY-MM-DD HH:mm):");
        String fromStr = ConsoleHelper.scanner.nextLine().trim();
        ConsoleHelper.printInfo("Enter end date & time (YYYY-MM-DD HH:mm):");
        String toStr = ConsoleHelper.scanner.nextLine().trim();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime from, to;
        try {
            from = LocalDateTime.parse(fromStr, dtf);
            to = LocalDateTime.parse(toStr, dtf);
        } catch (Exception e) {
            ConsoleHelper.printError("Invalid date format. Use YYYY-MM-DD HH:mm");
            return;
        }

        List<Transaction> list = purchaseHistoryService.inPeriod(from, to);
        if (list.isEmpty()) {
            ConsoleHelper.printWarning("No transactions in that period");
            return;
        }

        double totalRevenue = 0;
        ConsoleHelper.printInfo(String.format(
                "%-8s %-15s %-12s %-6s %-17s %s",
                "ID", "Drug", "Customer", "Qty", "Date Time", "Revenue"));
        DateTimeFormatter outFmt = dtf;
        for (Transaction t : list) {
            Drug d = drugStorage.getDrug(t.getDrugCode());
            String name = d != null ? d.getName() : t.getDrugCode();
            double revenue = t.getTotalCost();
            totalRevenue += revenue;

            System.out.printf(
                    "%-8s %-15s %-12s %-6d %-17s $%.2f\n",
                    t.getId(),
                    name,
                    t.getCustomerId(),
                    t.getQuantity(),
                    t.getDate().format(outFmt),
                    revenue);
        }
        ConsoleHelper.printSuccess(String.format("Total Revenue: $%.2f", totalRevenue));
    }

}