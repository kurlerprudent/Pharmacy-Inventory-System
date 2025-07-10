package com.pharmacy.ui;
import java.util.List;
import java.util.Scanner;

import com.pharmacy.logic.SupplierMapping;
import com.pharmacy.models.Supplier;


public class ConsoleSupplier {
    
    public static void main(String[] args) {
       
        SupplierMapping mapping = new SupplierMapping();
        mapping.loadFromFile();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- SUPPLIER MAPPING SYSTEM ---");
            System.out.println("1. Add Drug");
            System.out.println("2. Add Supplier to Drug");
            System.out.println("3. View Suppliers for a Drug");
            System.out.println("4. Filter Suppliers by Location");
            System.out.println("5. Filter Suppliers by Delivery Time");
            System.out.println("6. List All Drugs");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt(); scanner.nextLine(); // flush

            switch (choice) {
                case 1:
                    System.out.print("Enter drug code: ");
                    String code = scanner.nextLine();
                    System.out.print("Enter drug name: ");
                    String name = scanner.nextLine();
                    mapping.addDrug(code, name);
                    System.out.println("Drug added.");
                    break;

                case 2:
                    System.out.print("Enter drug code: ");
                    String drugCode = scanner.nextLine();
                    System.out.print("Enter supplier name: ");
                    String sname = scanner.nextLine();
                    System.out.print("Enter supplier location: ");
                    String loc = scanner.nextLine();
                    System.out.print("Enter delivery time (days): ");
                    int days = scanner.nextInt(); scanner.nextLine();
                    mapping.addSupplierToDrug(drugCode, new Supplier(sname, loc, drugCode, days));
                    System.out.println("Supplier added.");
                    break;

                case 3:
                    System.out.print("Enter drug code: ");
                    String code3 = scanner.nextLine();
                    List<Supplier> supps = mapping.getSuppliersForDrug(code3);
                    if (supps.isEmpty()) System.out.println("No suppliers found.");
                    else supps.forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("Enter location to search: ");
                    String location = scanner.nextLine();
                    List<Supplier> locs = mapping.filterByLocation(location);
                    if (locs.isEmpty()) System.out.println("No matches.");
                    else locs.forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("Enter max delivery time (days): ");
                    int max = scanner.nextInt(); scanner.nextLine();
                    List<Supplier> quick = mapping.filterByDeliveryTime(max);
                    if (quick.isEmpty()) System.out.println("No matches.");
                    else quick.forEach(System.out::println);
                    break;

                case 6:
                    mapping.listDrugs();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
