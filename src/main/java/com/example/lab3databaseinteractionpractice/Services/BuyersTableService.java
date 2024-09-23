package com.example.lab3databaseinteractionpractice.Services;

public class BuyersTableService {
    private final DatabaseService databaseService;
    private String sql;

    public BuyersTableService() {
        this.databaseService = new DatabaseService();
    }

    public String tableInitialization() {
        String createSql = "CREATE TABLE Buyers ("
                + "BuyerID INT PRIMARY KEY AUTO_INCREMENT, "
                + "BuyerName VARCHAR(255) NOT NULL, "
                + "ContactPhone VARCHAR(20) NOT NULL, "
                + "ContactEmail VARCHAR(255) NOT NULL"
                + ");";
        String insertSql = "INSERT INTO Buyers (BuyerName, ContactPhone, ContactEmail) VALUES "
                + "('Alice Johnson', '444-123-7890', 'alicejohnson@example.com'), "
                + "('Robert Garcia', '333-456-7890', 'robertgarcia@example.com'), "
                + "('Sophia Martinez', '222-654-1234', 'sophiamartinez@example.com'), "
                + "('James Rodriguez', '111-987-6543', 'jamesrodriguez@example.com'), "
                + "('Linda Lee', '999-888-7777', 'lindalee@example.com');";
        return databaseService.tableInitialization(createSql, insertSql, "Buyers");
    }
}
