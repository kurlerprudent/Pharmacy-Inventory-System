package com.pharmacy.logic;

import com.pharmacy.models.Drug;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortService {
    public List<Drug> sortByExpiry(List<Drug> drugs) {
        for (int i = 1; i < drugs.size(); i++) {
            Drug key = drugs.get(i);
            int j = i - 1;
            
            while (j >= 0 && drugs.get(j).getExpiry().isAfter(key.getExpiry())) {
                drugs.set(j + 1, drugs.get(j));
                j--;
            }
            drugs.set(j + 1, key);
        }
        return drugs;
    }

    public List<Drug> sortByPrice(List<Drug> drugs) {
        if (drugs.size() <= 1) return drugs;
        
        int mid = drugs.size() / 2;
        List<Drug> left = sortByPrice(drugs.subList(0, mid));
        List<Drug> right = sortByPrice(drugs.subList(mid, drugs.size()));
        
        return merge(left, right, Comparator.comparingDouble(Drug::getPrice));
    }
    
    public List<Drug> sortByQuantity(List<Drug> drugs) {
        drugs.sort(Comparator.comparingInt(Drug::getQuantity));
        return drugs;
    }
    
    public List<Drug> sortByCode(List<Drug> drugs) {
        drugs.sort(Comparator.comparing(Drug::getCode));
        return drugs;
    }
    
    private List<Drug> merge(List<Drug> left, List<Drug> right, Comparator<Drug> comp) {
        List<Drug> merged = new ArrayList();
        int i = 0, j = 0;
        
        while (i < left.size() && j < right.size()) {
            if (comp.compare(left.get(i), right.get(j)) < 0) {
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