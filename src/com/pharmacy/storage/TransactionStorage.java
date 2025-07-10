package com.pharmacy.storage;

import com.pharmacy.models.Transaction;
import com.pharmacy.utils.CSVUtils;
import com.pharmacy.utils.CustomQueue;
import com.pharmacy.utils.CustomStack;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionStorage {
    private final CustomQueue<Transaction> transactionHistory = new CustomQueue<>();
    private final CustomStack<Transaction> recentTransactions = new CustomStack<>();
    
    public TransactionStorage() {
        loadFromCSV();
    }
    
    private void loadFromCSV() {
        try {
            List<String[]> records = CSVUtils.readCSV("data/transactions.csv");
            for (String[] record : records) {
                if (record.length < 5) continue;
                
                Transaction transaction = new Transaction(
                    record[0], record[1], record[2], Integer.parseInt(record[3])
                );
                transaction.setDate(CSVUtils.parseDateTime(record[4]));
                addTransaction(transaction);
            }
        } catch (IOException e) {
            System.err.println("Error loading transactions from CSV: " + e.getMessage());
        }
    }
    
    private void saveToCSV(Transaction transaction) {
        try {
            CSVUtils.appendToCSV("data/transactions.csv", new String[]{
                transaction.getId(),
                transaction.getDrugCode(),
                transaction.getCustomerId(),
                String.valueOf(transaction.getQuantity()),
                CSVUtils.formatDateTime(transaction.getDate())
            });
        } catch (IOException e) {
            System.err.println("Error saving transaction to CSV: " + e.getMessage());
        }
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.enqueue(transaction);
        recentTransactions.push(transaction);
        saveToCSV(transaction);
    }

    public Transaction getNextTransaction() {
        return transactionHistory.dequeue();
    }

    public Transaction getRecentTransaction() {
        return recentTransactions.pop();
    }

    public List<Transaction> getAllTransactions() {
        return transactionHistory.getAll();
    }

    public List<Transaction> getRecentTransactions(int count) {
        return recentTransactions.getTopItems(count);
    }
}