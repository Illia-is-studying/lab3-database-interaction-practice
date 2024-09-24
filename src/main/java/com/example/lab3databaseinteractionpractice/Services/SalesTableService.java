package com.example.lab3databaseinteractionpractice.Services;

import com.example.lab3databaseinteractionpractice.Models.Sale;

import java.util.List;

public class SalesTableService {
    private final DatabaseService databaseService;
    private String sql;

    public SalesTableService() {
        this.databaseService = new DatabaseService();
    }

    public String tableInitialization() {
        String createSql = "CREATE TABLE Sales ("
                + "Id INT PRIMARY KEY AUTO_INCREMENT, "
                + "SellerId INT NOT NULL, "
                + "BuyerId INT NOT NULL, "
                + "ProductName VARCHAR(255) NOT NULL, "
                + "SellingPrice DECIMAL(10, 2) NOT NULL, "
                + "SaleDate DATE NOT NULL, "
                + "FOREIGN KEY (SellerId) REFERENCES Sellers(Id), "
                + "FOREIGN KEY (BuyerId) REFERENCES Buyers(Id)"
                + ");";
        String insertSql = "INSERT INTO Sales (SellerId, BuyerId, ProductName, SellingPrice, SaleDate) VALUES "
                + "(1, 1, 'Laptop', 1200.50, '2024-09-01'), "
                + "(2, 2, 'Smartphone', 799.99, '2024-09-02'), "
                + "(3, 3, 'Tablet', 450.00, '2024-09-03'), "
                + "(4, 4, 'Smartwatch', 199.99, '2024-09-04'), "
                + "(5, 5, 'Headphones', 150.00, '2024-09-05');";
        return databaseService.tableInitialization(createSql, insertSql, "Sales");
    }

}
