package com.pharmacy.models;

public class Supplier {
    private String id;
    private String name;
    private String contact;
    private String location;        // e.g. city or region
    private int turnaroundTime;     // in days

    public Supplier(String id,
                    String name,
                    String contact,
                    String location,
                    int turnaroundTime) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.location = location;
        this.turnaroundTime = turnaroundTime;
    }

    // Getters
    public String getId()             { return id; }
    public String getName()           { return name; }
    public String getContact()        { return contact; }
    public String getLocation()       { return location; }
    public int    getTurnaroundTime() { return turnaroundTime; }

    // Setters
    public void setId(String id)                   { this.id = id; }
    public void setName(String name)               { this.name = name; }
    public void setContact(String contact)         { this.contact = contact; }
    public void setLocation(String location)       { this.location = location; }
    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    @Override
    public String toString() {
        // CSV line: id,name,contact,location,turnaround
        return String.format("%s,%s,%s,%s,%d",
            id, name, contact, location, turnaroundTime);
    }
}
