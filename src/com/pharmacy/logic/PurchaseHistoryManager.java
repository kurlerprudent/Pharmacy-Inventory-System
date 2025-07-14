package com.pharmacy.logic;

import com.pharmacy.models.Transaction;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class PurchaseHistoryManager {
    private LinkedList<Transaction> transactions;
    private final String FILE_PATH = "data/transactions.csv";
    private int transactionCounter = 1;

    public PurchaseHistoryManager() {
        transactions = new LinkedList<>();
        loadTransactionsFromFile();
    }

    // Logs a new transaction with an auto-generated ID
    public void logTransaction(String drugCode, int quantity, String buyerId) {
        String id = generateTransactionId();
        Transaction txn = new Transaction(id, drugCode, buyerId, quantity);
        txn.setDate(LocalDateTime.now());
        transactions.add(txn);
        saveTransactionToFile(txn);
    }

    // Generates a transaction ID like T001, T002
    private String generateTransactionId() {
        return "T" + String.format("%03d", transactionCounter++);
    }

    // Saves a transaction to the CSV file
    private void saveTransactionToFile(Transaction txn) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(String.join(",",
                    txn.getId(),
                    txn.getDrugCode(),
                    txn.getCustomerId(),
                    String.valueOf(txn.getQuantity()),
                    txn.getDate().toString()
            ));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Loads all transactions from the CSV file
    private void loadTransactionsFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.toLowerCase().startsWith("id,")) continue;
                Transaction txn = Transaction.fromCSV(line);
                if (txn != null) {
                    transactions.add(txn);
                    updateCounterFromId(txn.getId());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transaction file: " + e.getMessage());
        }
    }

    // Ensures transactionCounter continues from last used ID
    private void updateCounterFromId(String id) {
        try {
            int num = Integer.parseInt(id.substring(1));
            if (num >= transactionCounter) {
                transactionCounter = num + 1;
            }
        } catch (Exception ignored) {
        }
    }

    // Returns all transactions sorted by date
    public List<Transaction> getSortedTransactionsByTime() {
        List<Transaction> sorted = new ArrayList<>(transactions);
        for (int i = 1; i < sorted.size(); i++) {
            Transaction key = sorted.get(i);
            int j = i - 1;
            while (j >= 0 && sorted.get(j).getDate().isAfter(key.getDate())) {
                sorted.set(j + 1, sorted.get(j));
                j--;
            }
            sorted.set(j + 1, key);
        }
        return sorted;
    }

    // Gets the last 5 transactions for a specific drug code
    public List<Transaction> getRecentTransactionsForDrug(String drugCode) {
        List<Transaction> result = new ArrayList<>();
        for (Transaction txn : transactions) {
            if (txn.getDrugCode().equalsIgnoreCase(drugCode)) {
                result.add(txn);
            }
        }

        result.sort((a, b) -> b.getDate().compareTo(a.getDate()));
        return result.size() > 5 ? result.subList(0, 5) : result;
    }

    // Print nicely formatted transactions
    public void printRecentTransactions(String drugCode) {
        List<Transaction> recent = getRecentTransactionsForDrug(drugCode);
        if (recent.isEmpty()) {
            System.out.println("No transactions found for drug code: " + drugCode);
            return;
        }

        System.out.println("Most recent purchases for [" + drugCode + "]:");
        for (Transaction txn : recent) {
            System.out.printf("ID: %s | Drug: %s | Qty: %d | Buyer: %s | Date: %s\n",
                    txn.getId(),
                    txn.getDrugCode(),
                    txn.getQuantity(),
                    txn.getCustomerId(),
                    txn.getDate()
            );
        }
    }
}
