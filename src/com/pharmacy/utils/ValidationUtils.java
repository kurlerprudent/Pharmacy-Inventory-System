package com.pharmacy.utils;

public class ValidationUtils {
    public static boolean isValidDrugCode(String code) {
        return code != null && code.matches("[A-Z]\\d{3}");
    }
    
    public static boolean isValidSupplierId(String id) {
        return id != null && id.matches("S\\d{3}");
    }
    
    public static boolean isValidQuantity(int quantity) {
        return quantity >= 0;
    }
    
    public static boolean isValidPrice(double price) {
        return price > 0;
    }
    
    public static boolean isValidCustomerId(String id) {
        return id != null && id.matches("C\\d{4}");
    }
}