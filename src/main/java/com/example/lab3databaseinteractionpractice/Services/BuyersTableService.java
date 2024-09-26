package com.example.lab3databaseinteractionpractice.Services;

import com.example.lab3databaseinteractionpractice.Models.SqlResult;
import com.example.lab3databaseinteractionpractice.Models.Trader;

import java.util.Arrays;
import java.util.List;

public class BuyersTableService {
    private final DatabaseService databaseService;
    private String sql;

    public BuyersTableService() {
        this.databaseService = new DatabaseService();
    }

    public List<String> getHeaders() {
        return Arrays.asList("Id",
                "Buyer Name",
                "Contact Phone",
                "Contact Email");
    }

    public String tableInitialization() {
        String createSql = "CREATE TABLE Buyers ("
                + "Id INT PRIMARY KEY AUTO_INCREMENT, "
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


    public List<List<String>> getAllBuyersAfterSettingQuery() {
        return databaseService.getAllEntitiesBySql(sql);
    }

    public SqlResult deleteBuyerById(String id) {
        sql = "DELETE FROM Buyers WHERE Id = '" + id + "';";
        return databaseService.executeUpdateBySql(sql);
    }

    public Trader getBuyerById(String id) {
        sql = "SELECT * FROM Buyers WHERE Id = " + id + ";";
        List<List<String>> entities = getAllBuyersAfterSettingQuery();

        Trader buyer = new Trader();
        try {
            List<String> entity = entities.get(0);
            buyer = ListConverterService.getObjectByListString(entity, buyer);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }

        return buyer;
    }

    public void addBuyer(Trader buyer) {
        sql = "INSERT INTO Buyers (BuyerName, ContactPhone, ContactEmail) VALUES('"
                + buyer.getName() + "', '"
                + buyer.getContactPhone() + "', '"
                + buyer.getContactEmail() + "');";
        databaseService.executeUpdateBySql(sql);
    }

    public void updateBuyer(Trader buyer) {
        sql = "UPDATE Buyers " +
                "SET BuyerName = '" + buyer.getName() +
                "', ContactPhone = '" + buyer.getContactPhone() +
                "', ContactEmail = '" + buyer.getContactEmail() +
                "' WHERE Id = '" + buyer.getId() + "';";
        databaseService.executeUpdateBySql(sql);
    }

    public void setInitialQueryGetAll() {
        sql = "SELECT * FROM Buyers";
    }

    public void setInitialQueryGetIdAndName() {
        sql = "SELECT Buyers.id, Buyers.BuyerName FROM Buyers";
    }
}
