package com.example.lab3databaseinteractionpractice.Services;

import com.example.lab3databaseinteractionpractice.Models.Seller;

import java.util.Arrays;
import java.util.List;

public class SellersTableService {
    private final DatabaseService databaseService;
    private String sql;

    public SellersTableService() {
        this.databaseService = new DatabaseService();
    }

    public List<String> getHeaders() {
        return Arrays.asList("Id",
                "Seller Name",
                "Contact Phone",
                "Contact Email");
    }

    public String tableInitialization() {
        String createSql = "CREATE TABLE Sellers ("
                + "Id INT PRIMARY KEY AUTO_INCREMENT, "
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

    public List<List<String>> getAllSellersAfterSettingQuery() {
        return databaseService.getAllEntitiesBySql(sql);
    }

    public void deleteSellerById(String id) {
        sql = "DELETE FROM Sellers WHERE Id = '" + id + "';";
        databaseService.executeUpdateBySql(sql);
    }

    public Seller getSellerById(String id) {
        sql = "SELECT * FROM Sellers WHERE Id = " + id + ";";
        List<List<String>> entities = getAllSellersAfterSettingQuery();

        Seller seller = new Seller();
        try {
            List<String> entity = entities.get(0);
            seller = ListConverterService.getObjectByListString(entity, seller);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }

        return seller;
    }

    public void addSeller(Seller seller) {
        sql = "INSERT INTO Sellers (SellerName, ContactPhone, ContactEmail) VALUES('"
                + seller.getSellerName() + "', '"
                + seller.getContactPhone() + "', '"
                + seller.getContactEmail() + "');";
        databaseService.executeUpdateBySql(sql);
    }

    public void updateSeller(Seller seller) {
        sql = "UPDATE Sellers " +
                "SET SellerName = '" + seller.getSellerName() +
                "', ContactPhone = '" + seller.getContactPhone() +
                "', ContactEmail = '" + seller.getContactEmail() +
                "' WHERE Id = '" + seller.getSellerID() + "';";
        databaseService.executeUpdateBySql(sql);
    }

    public void setInitialQuery() {
        sql = "SELECT * FROM Sellers";
    }
}
