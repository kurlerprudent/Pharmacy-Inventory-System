package com.pharmacy.logic;

import com.pharmacy.models.Customer;
import com.pharmacy.models.Transaction;
import com.pharmacy.storage.CustomerStorage;
import com.pharmacy.storage.TransactionStorage;

import java.util.List;

public class CustomerService {
    private final CustomerStorage   customerStorage;
    private final TransactionStorage transactionStorage;

    public CustomerService(CustomerStorage cs, TransactionStorage ts) {
        this.customerStorage = cs;
        this.transactionStorage = ts;
    }

    /** Fetch the customer record */
    public Customer getCustomer(String customerId) {
        return customerStorage.getCustomer(customerId);
    }

    /** Fetch all purchases made by that customer */
    public List<Transaction> getPurchaseHistory(String customerId) {
        return transactionStorage.getTransactionsForCustomer(customerId);
    }
}
