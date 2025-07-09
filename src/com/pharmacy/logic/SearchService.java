package com.pharmacy.logic;

import com.pharmacy.models.Drug;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements search algorithms manually
 */
public class SearchService {

    /**
     * Linear search by drug name
     * @param drugs List of drugs to search
     * @param name Drug name to find
     * @return Matching drugs (can be more than one with same name)
     */
    public List<Drug> linearSearchByName(List<Drug> drugs, String name) {
        System.out.println("Performing linear search for: " + name);
        List<Drug> result = new ArrayList<>();

        for (Drug drug : drugs) {
            if (drug.getName().equalsIgnoreCase(name)) {
                result.add(drug);
            }
        }

        return result;
    }

    /**
     * Binary search by drug code (requires list sorted by code)
     * @param drugs Sorted list of drugs
     * @param code Drug code to find
     * @return Drug object if found, null if not
     */
    public Drug binarySearchByCode(List<Drug> drugs, String code) {
        int low = 0;
        int high = drugs.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Drug midDrug = drugs.get(mid);
            int comparison = midDrug.getCode().compareToIgnoreCase(code);

            if (comparison == 0) {
                return midDrug;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return null; // Not found
    }

    /**
     * Linear search by supplier name
     * @param drugs List of drugs to search
     * @param supplier Supplier name to find
     * @return List of drugs supplied by the given supplier
     */
    public List<Drug> linearSearchBySupplier(List<Drug> drugs, String supplier) {
        System.out.println("Searching drugs by supplier: " + supplier);
        List<Drug> result = new ArrayList<>();

        for (Drug drug : drugs) {
            for (String s : drug.getSuppliers()) {
                if (s.trim().equalsIgnoreCase(supplier)) {
                    result.add(drug);
                    break; // No need to check other suppliers for this drug
                }
            }
        }

        return result;
    }
}
