package com.pharmacy.models;

public class Supplier {
    private String id;
    private String name;
    private String contact;

    public Supplier(String id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    
    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setContact(String contact) { this.contact = contact; }
    
    @Override
    public String toString() {
        return String.format("%s,%s,%s", id, name, contact);
    }
}