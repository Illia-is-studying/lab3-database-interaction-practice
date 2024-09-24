package com.example.lab3databaseinteractionpractice.Models;

public class Trader {
    private int id;
    private String name;
    private String contactPhone;
    private String contactEmail;

    public Trader() {
        this.id = 0;
        this.name = "";
        this.contactPhone = "";
        this.contactEmail = "";
    }

    public Trader(int id, String name, String contactPhone, String contactEmail) {
        this.id = id;
        this.name = name;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
