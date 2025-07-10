// package com.pharmacy.logic;

// import com.pharmacy.models.Transaction;

// import java.io.*;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.*;

// /**
//  * Manages the stack-based sales log and file storage
//  */
// public class SalesLogManager {
//     private Deque<Transaction> salesStack;
//     private static final String FILE_PATH = "data/saleslog.csv";
//     private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

//     public SalesLogManager() {
//         this.salesStack = new ArrayDeque<>();
//         loadSalesLog(); // Load existing records
//     }

//     public void recordSale(Transaction transaction) {
//         salesStack.push(transaction);
//         saveSaleToFile(transaction); // append new sale
//     }

//     public void viewSalesReport(LocalDate startDate, LocalDate endDate) {
//         boolean found = false;
//         System.out.println("\n📊 Sales Report (" + startDate + " to " + endDate + "):");
//         for (Transaction t : salesStack) {
//             LocalDate date = LocalDate.parse(t.getDate());
//             if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
//                 System.out.println(t);
//                 found = true;
//             }
//         }
//         if (!found) {
//             System.out.println("No sales found within the selected period.");
//         }
//     }

//     private void saveSaleToFile(Transaction tx) {
//         try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
//             writer.write(tx.toCSV());
//             writer.newLine();
//         } catch (IOException e) {
//             System.out.println("❌ Error writing to sales log: " + e.getMessage());
//         }
//     }

//     private void loadSalesLog() {
//         File file = new File(FILE_PATH);
//         if (!file.exists()) return;

//         try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
//             String line;
//             while ((line = reader.readLine()) != null) {
//                 Transaction tx = Transaction.fromCSV(line);
//                 if (tx != null) {
//                     salesStack.push(tx);
//                 }
//             }
//         } catch (IOException e) {
//             System.out.println("❌ Error loading sales log: " + e.getMessage());
//         }
//     }
// }
