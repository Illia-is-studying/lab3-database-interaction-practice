package com.example.lab3databaseinteractionpractice.Models;

public class Seller {
    private int sellerID;
    private String sellerName;
    private String contactPhone;
    private String contactEmail;

    public Seller() {
        this.sellerID = 0;
        this.sellerName = "";
        this.contactPhone = "";
        this.contactEmail = "";
    }

    public Seller(int sellerID, String sellerName, String contactPhone, String contactEmail) {
        this.sellerID = sellerID;
        this.sellerName = sellerName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
