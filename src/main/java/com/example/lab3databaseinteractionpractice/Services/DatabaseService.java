package com.example.lab3databaseinteractionpractice.Services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {
    private final String URL = "jdbc:mysql://localhost:3306/sales";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    public String tableInitialization(String createTableSql, String insertInTableSql, String tableName) {
        String html = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                Statement statement = connection.createStatement();

                statement.executeUpdate(createTableSql);
                html += "<h2>The table '" + tableName + "' created successfully!</h2>";

                int rows = statement.executeUpdate(insertInTableSql);
                html += "<h3>The table '" + tableName + "' was filled with " + rows + " rows:</h3>";
            }
        } catch (Exception ex) {
            html += "<h1>" + ex.getMessage() + "</h1>";
        }

        return html;
    }

    public List<String> getStringsBySql(String sql) {
        List<String> strings = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    strings.add(resultSet.getString(1));
                }
            }
        } catch (Exception ex) {
            System.out.println("\n\n\n" + ex.getMessage() + "\n\n\n");
        }

        return strings;
    }

    public List<List<String>> getAllEntitiesBySql(String sql) {
        List<List<String>> entities = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    List<String> entity = new ArrayList<>();

                    int count = 1;
                    while (count != 0) {
                        try {
                            String fieldValue = resultSet.getString(count);
                            entity.add(fieldValue);
                            count++;
                        } catch (SQLException ex) {
                            count = 0;
                        }
                    }

                    entities.add(entity);
                }
            }
        } catch (Exception ex) {
            System.out.println("\n\n\n" + ex.getMessage() + "\n\n\n");
        }

        return entities;
    }

    public void executeUpdateBySql(String sql) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                Statement statement = connection.createStatement();

                statement.executeUpdate(sql);
            }
        } catch (Exception ex) {
            System.out.println("\n\n\n" + ex.getMessage() + "\n\n\n");
        }
    }
}