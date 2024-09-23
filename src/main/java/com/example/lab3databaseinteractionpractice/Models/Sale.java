package com.example.lab3databaseinteractionpractice.Models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Sale {
    private int saleID;
    private int sellerID;
    private int buyerID;
    private long sellingPrice;
    private String productName;
    private LocalDate saleDate;

    public Sale() {
        saleID = 0;
        sellerID = 0;
        buyerID = 0;
        sellingPrice = 0;
        productName = "";
        saleDate = null;
    }

    public Sale(int saleID, int sellerID, int buyerID, long sellingPrice, String productName, LocalDate saleDate) {
        this.saleID = saleID;
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.productName = productName;
        this.sellingPrice = sellingPrice;
        this.saleDate = saleDate;
    }

    public int getSaleID() {
        return saleID;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public int getBuyerID() {
        return buyerID;
    }

    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
    }

    public String getProductName() {
        return productName;
    }

    public long getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(long sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }
}