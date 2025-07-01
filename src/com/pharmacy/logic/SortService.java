package com.pharmacy.logic;

import com.pharmacy.models.Drug;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements sorting algorithms manually
 */
public class SortService {

    /**
     * Insertion sort by drug name (A to Z)
     */
    public List<Drug> insertionSortByName(List<Drug> drugs) {
        List<Drug> sorted = new ArrayList<>(drugs);

        for (int i = 1; i < sorted.size(); i++) {
            Drug key = sorted.get(i);
            int j = i - 1;

            while (j >= 0 && sorted.get(j).getName().compareToIgnoreCase(key.getName()) > 0) {
                sorted.set(j + 1, sorted.get(j));
                j--;
            }

            sorted.set(j + 1, key);
        }

        return sorted;
    }

    /**
     * Merge sort by drug price (Low to High)
     */
    public List<Drug> mergeSortByPrice(List<Drug> drugs) {
        if (drugs.size() <= 1) return drugs;

        int mid = drugs.size() / 2;

        List<Drug> left = mergeSortByPrice(new ArrayList<>(drugs.subList(0, mid)));
        List<Drug> right = mergeSortByPrice(new ArrayList<>(drugs.subList(mid, drugs.size())));

        return mergeByPrice(left, right);
    }

    private List<Drug> mergeByPrice(List<Drug> left, List<Drug> right) {
        List<Drug> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getPrice() <= right.get(j).getPrice()) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }

        while (i < left.size()) merged.add(left.get(i++));
        while (j < right.size()) merged.add(right.get(j++));

        return merged;
    }
}
