// package com.pharmacy.logic;

// import com.pharmacy.models.Drug;
// import java.util.ArrayList;
// import java.util.List;

// public class SearchService {

//     public Drug linearSearchByCode(List<Drug> drugs, String code) {
//         for (Drug drug : drugs) {
//             if (drug.getCode().equalsIgnoreCase(code)) {
//                 return drug;
//             }
//         }
//         return null;
//     }

//     public List<Drug> linearSearchByName(List<Drug> drugs, String name) {
//         List<Drug> results = new ArrayList<>();
//         for (Drug drug : drugs) {
//             if (drug.getName().toLowerCase().contains(name.toLowerCase())) {
//                 results.add(drug);
//             }
//         }
//         return results;
//     }

//     public Drug binarySearchByCode(List<Drug> sortedDrugs, String code) {
//         int low = 0;
//         int high = sortedDrugs.size() - 1;
        
//         while (low <= high) {
//             int mid = low + (high - low) / 2;
//             Drug midDrug = sortedDrugs.get(mid);
//             int cmp = midDrug.getCode().compareToIgnoreCase(code);
            
//             if (cmp == 0) {
//                 return midDrug;
//             } else if (cmp < 0) {
//                 low = mid + 1;
//             } else {
//                 high = mid - 1;
//             }
//         }
//         return null;
//     }
// }



package com.pharmacy.logic;

import com.pharmacy.models.Drug;
import com.pharmacy.storage.DrugStorage;
import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private final DrugStorage drugStorage;

    public SearchService(DrugStorage drugStorage) {
        this.drugStorage = drugStorage;
    }

    public List<Drug> searchByName(String name) {
        List<Drug> results = new ArrayList<>();
        for (Drug drug : drugStorage.getAllDrugs()) {
            if (drug.getName().toLowerCase().contains(name.toLowerCase())) {
                results.add(drug);
            }
        }
        return results;
    }

    public List<Drug> searchBySupplier(String supplierId) {
        return drugStorage.getDrugsBySupplier(supplierId);
    }

    public Drug binarySearchByCode(String code) {
        List<Drug> sorted = new SortService().sortByCode(drugStorage.getAllDrugs());
        int low = 0;
        int high = sorted.size() - 1;
        
        while (low <= high) {
            int mid = (low + high) / 2;
            Drug midDrug = sorted.get(mid);
            int cmp = midDrug.getCode().compareTo(code);
            
            if (cmp == 0) return midDrug;
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }
}