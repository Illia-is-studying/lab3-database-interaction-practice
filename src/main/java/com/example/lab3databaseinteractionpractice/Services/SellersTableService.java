package com.example.lab3databaseinteractionpractice.Services;

public class SellersTableService {
    private final DatabaseService databaseService;
    private String sql;

    public SellersTableService() {
        this.databaseService = new DatabaseService();
    }

    public String tableInitialization() {
        String createSql = "CREATE TABLE Sellers ("
                + "SellerID INT PRIMARY KEY AUTO_INCREMENT, "
                + "SellerName VARCHAR(255) NOT NULL, "
                + "ContactPhone VARCHAR(20) NOT NULL, "
                + "ContactEmail VARCHAR(255) NOT NULL"
                + ");";
        String insertSql = "INSERT INTO Sellers (SellerName, ContactPhone, ContactEmail) VALUES "
                + "('John Doe', '123-456-7890', 'johndoe@example.com'), "
                + "('Jane Smith', '987-654-3210', 'janesmith@example.com'), "
                + "('Michael Brown', '555-123-4567', 'michaelbrown@example.com'), "
                + "('Emily Davis', '555-987-6543', 'emilydavis@example.com'), "
                + "('David Wilson', '555-555-5555', 'davidwilson@example.com');";
        return databaseService.tableInitialization(createSql, insertSql, "Sellers");
    }
}
