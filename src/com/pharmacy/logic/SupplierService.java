package com.pharmacy.logic;

import com.pharmacy.models.Supplier;
import com.pharmacy.storage.SupplierStorage;
import java.util.List;
import java.util.stream.Collectors;

public class SupplierService {
    private final SupplierStorage storage;

    public SupplierService(SupplierStorage storage) {
        this.storage = storage;
    }

    /** Return all suppliers in the given location (case-insensitive) */
    public List<Supplier> filterByLocation(String location) {
        String locLower = location.toLowerCase();
        return storage.getAllSuppliers().stream()
            .filter(s -> s.getLocation().toLowerCase().contains(locLower))
            .collect(Collectors.toList());
    }

    /** Return suppliers whose turnaround time is <= maxDays */
    public List<Supplier> filterByTurnaround(int maxDays) {
        return storage.getAllSuppliers().stream()
            .filter(s -> s.getTurnaroundTime() <= maxDays)
            .collect(Collectors.toList());
    }
}
