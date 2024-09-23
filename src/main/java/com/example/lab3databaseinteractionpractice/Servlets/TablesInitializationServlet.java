package com.example.lab3databaseinteractionpractice.Servlets;

import com.example.lab3databaseinteractionpractice.Services.BuyersTableService;
import com.example.lab3databaseinteractionpractice.Services.SalesTableService;
import com.example.lab3databaseinteractionpractice.Services.SellersTableService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "tablesInitializationServlet", value = "/tables-initialization-servlet")
public class TablesInitializationServlet extends HttpServlet {
    private BuyersTableService buyersTableService;
    private SellersTableService sellersTableService;
    private SalesTableService salesTableService;

    @Override
    public void init() throws ServletException {
        buyersTableService = new BuyersTableService();
        sellersTableService = new SellersTableService();
        salesTableService = new SalesTableService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String initialResult = buyersTableService.tableInitialization();
        initialResult += sellersTableService.tableInitialization();
        initialResult += salesTableService.tableInitialization();

        request.setAttribute("initialResult", initialResult);
        request.getRequestDispatcher("/tables-initialization.jsp").forward(request, response);
    }

}
