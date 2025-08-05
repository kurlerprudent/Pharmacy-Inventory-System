package com.pharmacy.utils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class CSVUtils {
  

    // Read CSV and return list of rows
    public static List<String[]> readCSV(String filePath) throws IOException {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // skip header
            String line;
            while ((line = br.readLine()) != null) {
                // split into exactly 5 parts so we keep location intact
                String[] parts = line.split(",", 6);
                records.add(parts);
            }
        }
        return records;
    }

    // Append row to CSV
    public static void appendToCSV(String filePath, String[] data) throws IOException {
        String header = "";
        if (!Files.exists(Paths.get(filePath))) {
            switch (filePath) {
                case "data/drugs.csv":
                    header = "code,name,quantity,price,expiry,supplier_ids\n";
                    break;
                case "data/suppliers.csv":
                    header = "id,name,contact,location,turnaround_days\n";
                    break;
                case "data/customers.csv":
                    header = "id,name,phone\n";
                    break;
                case "data/transactions.csv":
                    header = "id,drug_code,customer_id,quantity,date,total_cost\n";
                    break;
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
    public static LocalDateTime parseDateTime(String str) {
        try {
            return LocalDateTime.parse(str, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            // Try fallback: manually add seconds if missing
            if (str.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}")) {
                str += ":00"; // add seconds
                return LocalDateTime.parse(str, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
            throw e;
        }
    }
}