package com.pharmacy.storage;

import com.pharmacy.models.Customer;
import com.pharmacy.utils.CSVUtils;
import java.io.IOException;
import java.util.*;

public class CustomerStorage {
    private final Map<String, Customer> customerMap = new HashMap<>();
    
    public CustomerStorage() {
        loadFromCSV();
    }
    
    private void loadFromCSV() {
        try {
            List<String[]> records = CSVUtils.readCSV("data/customers.csv");
            for (String[] record : records) {
                if (record.length < 3) continue;
                addCustomer(new Customer(record[0], record[1], record[2]));
            }
        } catch (IOException e) {
            System.err.println("Error loading customers from CSV: " + e.getMessage());
        }
    }
    
    private void saveToCSV(Customer customer) {
        try {
            CSVUtils.appendToCSV("data/customers.csv", new String[]{
                customer.getId(),
                customer.getName(),
                customer.getPhone()
            });
        } catch (IOException e) {
            System.err.println("Error saving customer to CSV: " + e.getMessage());
        }
    }

    public void addCustomer(Customer customer) {
        customerMap.put(customer.getId(), customer);
        saveToCSV(customer);
    }
    
    public Customer getCustomer(String id) {
        return customerMap.get(id);
    }
    
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerMap.values());
    }
}