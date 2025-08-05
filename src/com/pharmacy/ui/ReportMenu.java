package com.pharmacy.ui;

import com.pharmacy.logic.CustomerService;
import com.pharmacy.logic.StockMonitor;
import com.pharmacy.logic.SupplierService;
import com.pharmacy.models.Drug;
import com.pharmacy.models.Supplier;
import com.pharmacy.models.Transaction;
import com.pharmacy.storage.CustomerStorage;
import com.pharmacy.storage.DrugStorage;
import com.pharmacy.storage.SupplierStorage;
import com.pharmacy.storage.TransactionStorage;
import java.util.List;

public class ReportMenu {
    private final StockMonitor stockMonitor;
    private final DrugStorage drugStorage;
    private final TransactionStorage transactionStorage;
    private final SupplierStorage supplierStorage;

    public ReportMenu(StockMonitor stockMonitor,
            DrugStorage drugStorage,
            TransactionStorage transactionStorage,
            SupplierStorage supplierStorage) {
        this.stockMonitor = stockMonitor;
        this.drugStorage = drugStorage;
        this.transactionStorage = transactionStorage;
        this.supplierStorage = supplierStorage;
    }

    public void showMenu() {
        while (true) {
            ConsoleHelper.printHeader("REPORTING");
            System.out.println("1. Low Stock Report");
            System.out.println("2. Expiry Report");
            System.out.println("3. Supplier Report");
            System.out.println("4. Sales Report");
            System.out.println("5. Filter Suppliers");
            System.out.println("6. Customer Purchase History");
            System.out.println("7. Back to Main Menu");
            System.out.print("Enter choice: ");

            int choice = ConsoleHelper.getIntInput();

            switch (choice) {
                case 1:
                    lowStockReport();
                    break;
                case 2:
                    expiryReport();
                    break;
                case 3:
                    supplierReport();
                    break;
                case 4:
                    salesReport();
                    break;
                case 5:
                    filterSuppliers();
                    break;
                case 6:
                    customerPurchaseHistory();
                    break;
                case 7:
                    return;
                default:
                    ConsoleHelper.printError("Invalid choice");
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
            if (drug == null)
                continue;

            double revenue = drug.getPrice() * t.getQuantity();
            totalRevenue += revenue;

            System.out.printf("%-8s %-15s %-10s %-5d %s $%.2f\n",
                    t.getId(),
                    drug.getName(),
                    t.getCustomerId(),
                    t.getQuantity(),
                    t.getDate().toString().replace("T", " "),
                    revenue);
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
    }

    private void filterSuppliers() {
        ConsoleHelper.printHeader("FILTER SUPPLIERS");
        System.out.println("1. By Location");
        System.out.println("2. By Turnaround Time");
        System.out.print("Enter choice: ");
        int choice = ConsoleHelper.getIntInput();

        SupplierService svc = new SupplierService(this.supplierStorage);
        List<Supplier> results;

        switch (choice) {
            case 1:
                System.out.print("Enter location to filter: ");
                String loc = ConsoleHelper.scanner.nextLine().trim();
                results = svc.filterByLocation(loc);
                break;
            case 2:
                System.out.print("Enter max turnaround days: ");
                int days = ConsoleHelper.getIntInput();
                results = svc.filterByTurnaround(days);
                break;
            default:
                ConsoleHelper.printError("Invalid choice");
                return;
        }

        if (results.isEmpty()) {
            ConsoleHelper.printWarning("No suppliers match the criteria");
        } else {
            ConsoleHelper.printInfo("\nID    Name           Contact       Location     Turnaround");
            for (Supplier s : results) {
                System.out.printf("%-5s %-14s %-13s %-12s %2d days\n",
                        s.getId(), s.getName(), s.getContact(),
                        s.getLocation(), s.getTurnaroundTime());
            }
        }
    }

    private void customerPurchaseHistory() {
        ConsoleHelper.printHeader("CUSTOMER PURCHASE HISTORY");
        System.out.print("Enter customer ID: ");
        String cid = ConsoleHelper.scanner.nextLine().trim();

        CustomerService cs = new CustomerService(
                new CustomerStorage(), transactionStorage);
        var customer = cs.getCustomer(cid);
        if (customer == null) {
            ConsoleHelper.printError("Customer not found: " + cid);
            return;
        }

        var history = cs.getPurchaseHistory(cid);
        if (history.isEmpty()) {
            ConsoleHelper.printWarning("No transactions found for " + customer.getName());
            return;
        }

        // Print header
        ConsoleHelper.printInfo(String.format(
                "%-8s %-10s %-8s %-17s",
                "TransID", "Drug", "Qty", "Date Time"));
        for (var t : history) {
            System.out.printf("%-8s %-10s %-8d %-17s\n",
                    t.getId(),
                    t.getDrugCode(),
                    t.getQuantity(),
                    t.getDate().toString().replace("T", " "));
        }
        ConsoleHelper.printInfo("Total purchases: " + history.size());
    }

}
