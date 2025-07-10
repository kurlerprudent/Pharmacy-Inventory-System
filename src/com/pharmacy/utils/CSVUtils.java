package com.pharmacy.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;

public class CSVUtils {
    // Handle both ISO format and space-separated formats
    private static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM-dd[['T'][ ]HH:mm:ss")
        .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
        .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
        .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
        .toFormatter();
    
    // Read CSV and return list of rows
    public static List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                records.add(values);
            }
        }
        return records;
    }
    
    // Append row to CSV
    public static void appendToCSV(String filePath, String[] data) throws IOException {
        String header = "";
        if (!Files.exists(Paths.get(filePath))) {
            switch (filePath) {
                case "data/drugs.csv": header = "code,name,quantity,price,expiry,supplier_id\n"; break;
                case "data/suppliers.csv": header = "id,name,contact\n"; break;
                case "data/customers.csv": header = "id,name,phone\n"; break;
                case "data/transactions.csv": header = "id,drug_code,customer_id,quantity,date\n"; break;
            }
            Files.createDirectories(Paths.get(filePath).getParent());
        }
        
        try (BufferedWriter writer = new BufferedWriter(
             new FileWriter(filePath, true))) {
            if (!header.isEmpty()) {
                writer.write(header);
            }
            writer.write(String.join(",", data) + "\n");
        }
    }
    
    // Format LocalDateTime for CSV
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
    
    // Parse CSV date string to LocalDateTime
    public static LocalDateTime parseDateTime(String dateStr) {
        return LocalDateTime.parse(dateStr, DATE_FORMATTER);
    }
}