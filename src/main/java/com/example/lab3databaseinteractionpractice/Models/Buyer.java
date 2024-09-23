package com.example.lab3databaseinteractionpractice.Models;

public class Buyer {
    private int buyerID;
    private String buyerName;
    private String contactPhone;
    private String contactEmail;

    public Buyer() {
        this.buyerID = 0;
        this.buyerName = "";
        this.contactPhone = "";
        this.contactEmail = "";
    }

    public Buyer(int buyerID, String buyerName, String contactPhone, String contactEmail) {
        this.buyerID = buyerID;
        this.buyerName = buyerName;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
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
