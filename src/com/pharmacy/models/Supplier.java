package com.pharmacy.models;

/**
 * Represents a drug supplier
 * Properties: ID, name, location, delivery time
 */
public class Supplier {
    private String id;
    public String name;
    public String location;
    public int deliveryTime; // in days
    
    public Supplier(String id, String name, String location, int deliveryTime) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.deliveryTime = deliveryTime;
    }


    // Getters and setters for the properties

     public String getId() { return id ;}
    public void setId (String id) {this.id = id;}

    public String getName() { return name ;}
    public void setName (String name) {this.name = name;}

    public String getLocation() { return location ;}
    public void setLocation (String location) {this.location = location;}

    public int getDeliverytime() { return deliveryTime ;}
    public void setDeliverytime (int deliveryTime) {this.deliveryTime = deliveryTime;}



    

    
    

    public String toString() {
        return name + "| location: " + location + "| Delivery: " + deliveryTime + "| time: ";
    }
    
}

   
 