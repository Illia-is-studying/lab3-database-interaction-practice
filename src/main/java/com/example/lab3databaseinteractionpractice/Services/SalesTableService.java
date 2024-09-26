package com.example.lab3databaseinteractionpractice.Services;

import com.example.lab3databaseinteractionpractice.Models.Sale;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class SalesTableService {
    private final DatabaseService databaseService;
    private String sql;

    public SalesTableService() {
        this.databaseService = new DatabaseService();
    }

    public List<String> getHeaders() {
        return Arrays.asList("Id",
                "Seller Name",
                "Buyer Name",
                "Product Name",
                "Selling Price",
                "Sale Date");
    }

    public String tableInitialization() {
        String createSql = "CREATE TABLE Sales ("
                + "Id INT PRIMARY KEY AUTO_INCREMENT, "
                + "SellerId INT NOT NULL, "
                + "BuyerId INT NOT NULL, "
                + "ProductName VARCHAR(255) NOT NULL, "
                + "SellingPrice DECIMAL(10, 0) NOT NULL, "
                + "SaleDate DATE NOT NULL, "
                + "FOREIGN KEY (SellerId) REFERENCES Sellers(Id), "
                + "FOREIGN KEY (BuyerId) REFERENCES Buyers(Id)"
                + ");";
        String insertSql = "INSERT INTO Sales (SellerId, BuyerId, ProductName, SellingPrice, SaleDate) VALUES "
                + "(1, 1, 'Laptop', 1200, '2024-09-01'), "
                + "(2, 2, 'Smartphone', 799, '2024-09-02'), "
                + "(3, 3, 'Tablet', 450, '2024-09-03'), "
                + "(4, 4, 'Smartwatch', 199, '2024-09-04'), "
                + "(5, 5, 'Headphones', 150, '2024-09-05');";
        return databaseService.tableInitialization(createSql, insertSql, "Sales");
    }

    public List<List<String>> getAllSalesAfterSettingQuery() {
        return databaseService.getAllEntitiesBySql(sql);
    }

    public void deleteSalesById(String id) {
        sql = "DELETE FROM Sales WHERE Id = '" + id + "';";
        databaseService.executeUpdateBySql(sql);
    }

    public Sale getSaleById(String id) {
        sql = "SELECT * FROM Sales WHERE Id = " + id + ";";
        List<List<String>> entities = getAllSalesAfterSettingQuery();

        Sale sale = new Sale();
        try {
            List<String> entity = entities.get(0);
            sale = ListConverterService.getObjectByListString(entity, sale);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }

        return sale;
    }

    public void addSale(Sale sale) {
        sql = "INSERT INTO Sales (SellerId, BuyerId, ProductName, SellingPrice, SaleDate) VALUES('"
                + sale.getSellerID() + "', '"
                + sale.getBuyerID() + "', '"
                + sale.getProductName() + "', '"
                + sale.getSellingPrice() + "', '"
                + Date.valueOf(sale.getSaleDate()) + "');";
        databaseService.executeUpdateBySql(sql);
    }

    public void updateSale(Sale sale) {
        sql = "UPDATE Sales " +
                "SET SellerId = '" + sale.getSellerID() +
                "', BuyerId = '" + sale.getBuyerID() +
                "', ProductName = '" + sale.getProductName() +
                "', SellingPrice = '" + sale.getSellingPrice() +
                "', SaleDate = '" + sale.getSaleDate() +
                "' WHERE Id = '" + sale.getSaleID() + "';";
        databaseService.executeUpdateBySql(sql);
    }

    public void setSellerIdCondition(String id) {
        addConditionToSql("SellerId = '" + id + "'");
    }

    public void setBuyerIdCondition(String id) {
        addConditionToSql("BuyerId = '" + id + "'");
    }

    public void setSaleDateRangeCondition(String lowerDate, String upperDate) {
        addConditionToSql("SaleDate BETWEEN '" + Date.valueOf(lowerDate) + "' AND '" + Date.valueOf(upperDate) + "'");
    }

    public void setSaleDateCondition(String date) {
        addConditionToSql("SaleDate = '" + Date.valueOf(date) + "'");
    }

    public void setInitialQueryGetAll() {
        sql = "SELECT * FROM Sales";
    }

    private void addConditionToSql(String condition) {
        if (sql.contains("WHERE")) {
            sql += " AND " + condition;
        } else {
            sql += " WHERE " + condition;
        }
    }
}
