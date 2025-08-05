package com.pharmacy.ui;

import com.pharmacy.models.Drug;
import java.util.Scanner;

public class ConsoleHelper {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    
    public static final Scanner scanner = new Scanner(System.in);
    
    public static void printSuccess(String message) {
        System.out.println(GREEN + message + RESET);
    }
    
    public static void printError(String message) {
        System.out.println(RED + "ERROR: " + message + RESET);
    }
    
    public static void printWarning(String message) {
        System.out.println(YELLOW + "WARNING: " + message + RESET);
    }
    
    public static void printInfo(String message) {
        System.out.println(CYAN + message + RESET);
    }
    
    public static void printHeader(String header) {
        System.out.println(PURPLE + "\n\t " + header + "\t" + RESET);
    }
    
    public static void printDrug(Drug drug) {
        String color = RESET;
        if (drug.getQuantity() < 10) color = RED;
        else if (drug.getQuantity() < 25) color = YELLOW;
        
        System.out.printf(color + "%-8s %-20s %-8d $%-8.2f %-12s %s\n" + RESET,
            drug.getCode(), drug.getName(), drug.getQuantity(), 
            drug.getPrice(), drug.getExpiry(), drug.getSupplierIds());
    }
    
    public static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                printError("Invalid number. Please enter an integer.");
            }
        }
    }
    
    public static double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                printError("Invalid number. Please enter a decimal value.");
            }
        }
    }
}