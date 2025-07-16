package com.pharmacy.storage;

import com.pharmacy.models.Transaction;
import com.pharmacy.utils.CSVUtils;
import com.pharmacy.utils.CustomQueue;
import com.pharmacy.utils.CustomStack;
import java.util.stream.Collectors;
import java.util.List;
import java.io.IOException;
import java.time.LocalDateTime;


public class TransactionStorage {
    private final CustomQueue<Transaction> transactionHistory = new CustomQueue<>();
    private final CustomStack<Transaction> recentTransactions   = new CustomStack<>();

    public TransactionStorage() {
        loadFromCSV();
    }

    private void loadFromCSV() {
        try {
            List<String[]> records = CSVUtils.readCSV("data/transactions.csv");
            for (String[] r : records) {
                if (r.length < 6) continue;
                Transaction t = new Transaction(
                    r[0],         // id
                    r[1],         // drugCode
                    r[2],         // customerId
                    Integer.parseInt(r[3]), // quantity
                    Double.parseDouble(r[5])// totalCost
                );
                t.setDateFromString(r[4]);
                transactionHistory.enqueue(t);
                recentTransactions.push(t);
            }
        } catch (IOException e) {
            System.err.println("Error loading transactions: " + e.getMessage());
        }
    }

    private void saveToCSV(Transaction t) {
        try {
            // toString yields one CSV row; split into 6 parts
            String[] parts = t.toString().split(",", 6);
            CSVUtils.appendToCSV("data/transactions.csv", parts);
        } catch (IOException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
        }
    }

    public void addTransaction(Transaction t) {
        transactionHistory.enqueue(t);
        recentTransactions.push(t);
        saveToCSV(t);
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

    /** NEW: get the most recent N purchases of a specific drug */
    public List<Transaction> getRecentTransactionsForDrug(String drugCode, int count) {
        return recentTransactions.getTopItems(recentTransactions.size()).stream()
            .filter(t -> t.getDrugCode().equals(drugCode))
            .limit(count)
            .collect(Collectors.toList());
    }

    /** NEW: get all transactions within [from, to] */
    public List<Transaction> getTransactionsInPeriod(LocalDateTime from, LocalDateTime to) {
        return transactionHistory.getAll().stream()
            .filter(t -> !t.getDate().isBefore(from) && !t.getDate().isAfter(to))
            .collect(Collectors.toList());
    }

     public List<Transaction> getTransactionsForCustomer(String customerId) {
        return transactionHistory.getAll().stream()
                       .filter(t -> t.getCustomerId().equals(customerId))
                       .collect(Collectors.toList());
    }
}
