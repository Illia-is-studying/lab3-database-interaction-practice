package com.example.lab3databaseinteractionpractice.Services;

import com.example.lab3databaseinteractionpractice.Models.SqlResult;
import com.example.lab3databaseinteractionpractice.Models.Trader;

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

    public SqlResult deleteSellerById(String id) {
        sql = "DELETE FROM Sellers WHERE Id = '" + id + "';";
        return databaseService.executeUpdateBySql(sql);
    }

    public Trader getSellerById(String id) {
        sql = "SELECT * FROM Sellers WHERE Id = " + id + ";";
        List<List<String>> entities = getAllSellersAfterSettingQuery();

        Trader seller = new Trader();
        try {
            List<String> entity = entities.get(0);
            seller = ListConverterService.getObjectByListString(entity, seller);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }

        return seller;
    }

    public void addSeller(Trader seller) {
        sql = "INSERT INTO Sellers (SellerName, ContactPhone, ContactEmail) VALUES('"
                + seller.getName() + "', '"
                + seller.getContactPhone() + "', '"
                + seller.getContactEmail() + "');";
        databaseService.executeUpdateBySql(sql);
    }

    public void updateSeller(Trader seller) {
        sql = "UPDATE Sellers " +
                "SET SellerName = '" + seller.getName() +
                "', ContactPhone = '" + seller.getContactPhone() +
                "', ContactEmail = '" + seller.getContactEmail() +
                "' WHERE Id = '" + seller.getId() + "';";
        databaseService.executeUpdateBySql(sql);
    }

    public void setInitialQueryGetAll() {
        sql = "SELECT * FROM Sellers";
    }

    public void setInitialQueryGetIdAndName() {
        sql = "SELECT Sellers.id, Sellers.SellerName FROM Sellers";
    }
}
