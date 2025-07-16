package com.pharmacy.logic;

import com.pharmacy.models.Transaction;
import com.pharmacy.storage.TransactionStorage;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseHistoryService {
    private final TransactionStorage storage;

    public PurchaseHistoryService(TransactionStorage storage) {
        this.storage = storage;
    }

    public List<Transaction> recentForDrug(String drugCode, int n) {
        return storage.getRecentTransactionsForDrug(drugCode, n);
    }

    public List<Transaction> inPeriod(LocalDateTime from, LocalDateTime to) {
        return storage.getTransactionsInPeriod(from, to);
    }
}
