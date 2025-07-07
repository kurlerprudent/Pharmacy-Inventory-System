package com.pharmacy.logic;

import com.pharmacy.models.Drug;
import java.util.List;

/**
 * Implements search algorithms manually
 */
public class SearchService {
    /**
     * Linear search by drug name
     * @param drugs List of drugs to search
     * @param name Drug name to find
     * @return Matching drugs
     */
    public List<Drug> linearSearchByName(List<Drug> drugs, String name) {
        // TODO: Implement linear search
        System.out.println("Performing linear search for: " + name);
        return null;
    }
    
    /**
     * Binary search by drug code (requires sorted list)
     * @param drugs Sorted list of drugs
     * @param code Drug code to find
     * @return Drug object if found
     */
    public Drug binarySearchByCode(List<Drug> drugs, String code) {
        // TODO: Implement binary search
        return null;
    }
}