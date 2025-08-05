// package com.pharmacy.ui;

// import java.util.Scanner;


// public abstract class BaseMenu {
//     protected static final Scanner scanner = new Scanner(System.in);
    
//     public abstract void show();
    
//     protected int getIntInput(String prompt, int min, int max) {
//         while (true) {
//             try {
//                 System.out.print(prompt);
//                 String input = scanner.nextLine().trim();
                
//                 if (input.isEmpty()) {
//                     throw new IllegalArgumentException("Input cannot be empty");
//                 }
                
//                 int value = Integer.parseInt(input);
                
//                 if (value < min || value > max) {
//                     throw new IllegalArgumentException(
//                         String.format("Please enter between %d and %d", min, max)
//                     );
//                 }
                
//                 return value;
//             } catch (NumberFormatException e) {
//                 ConsoleHelper.printError("Invalid number. Please enter digits only.");
//             } catch (IllegalArgumentException e) {
//                 ConsoleHelper.printError(e.getMessage());
//             }
//         }
//     }
    
//     protected double getDoubleInput(String prompt, double min, double max) {
//         while (true) {
//             try {
//                 System.out.print(prompt);
//                 String input = scanner.nextLine().trim();
                
//                 if (input.isEmpty()) {
//                     throw new IllegalArgumentException("Input cannot be empty");
//                 }
                
//                 double value = Double.parseDouble(input);
                
//                 if (value < min || value > max) {
//                     throw new IllegalArgumentException(
//                         String.format("Value must be between %.2f and %.2f", min, max)
//                     );
//                 }
                
//                 return value;
//             } catch (NumberFormatException e) {
//                 ConsoleHelper.printError("Invalid number. Please enter a decimal number.");
//             } catch (IllegalArgumentException e) {
//                 ConsoleHelper.printError(e.getMessage());
//             }
//         }
//     }
    
//     protected String getStringInput(String prompt) {
//         while (true) {
//             System.out.print(prompt);
//             String input = scanner.nextLine().trim();
            
//             if (input.isEmpty()) {
//                 ConsoleHelper.printError("Input cannot be empty. Please try again.");
//             } else {
//                 return input;
//             }
//         }
//     }
    
//     protected boolean getConfirmation(String prompt) {
//         while (true) {
//             System.out.print(prompt + " (y/n): ");
//             String input = scanner.nextLine().trim().toLowerCase();
            
//             if (input.equals("y")) {
//                 return true;
//             } else if (input.equals("n")) {
//                 return false;
//             } else {
//                 ConsoleHelper.printError("Please enter 'y' or 'n'.");
//             }
//         }
//     }
    
//     protected void pressEnterToContinue() {
//         System.out.print("\nPress Enter to continue...");
//         scanner.nextLine();
//     }
    
//     protected void clearScreen() {
//         System.out.print("\033[H\033[2J");
//         System.out.flush();
//     }
// }