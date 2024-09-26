package com.example.lab3databaseinteractionpractice.Servlets;

import com.example.lab3databaseinteractionpractice.Models.SqlResult;
import com.example.lab3databaseinteractionpractice.Models.Trader;
import com.example.lab3databaseinteractionpractice.Services.BuyersTableService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "buyersServlet", value = "/buyers-servlet")
public class BuyersServlet extends HttpServlet {
    private BuyersTableService buyersTableService;

    @Override
    public void init() throws ServletException {
        buyersTableService = new BuyersTableService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        String id = request.getParameter("id");

        request.setAttribute("method", method);

        if (method == null || method.equals("get")) {
            buyersTableService.setInitialQueryGetAll();
            List<List<String>> buyersRows = buyersTableService.getAllBuyersAfterSettingQuery();

            request.setAttribute("tableName", "Buyers Table");
            request.setAttribute("servletName", "buyers-servlet");
            request.setAttribute("rowsAttr", buyersRows);
            request.setAttribute("headersAttr", buyersTableService.getHeaders());
            request.getRequestDispatcher("traders.jsp").forward(request, response);
        } else if (method.equals("post") || method.equals("put")) {
            Trader buyer = new Trader();

            if (method.equals("put")) {
                buyer = buyersTableService.getBuyerById(id);

                if (buyer.getId() < 1) {
                    request.setAttribute("method", "post");
                }
            }

            request.setAttribute("formName", "Buyer");
            request.setAttribute("trader", buyer);
            request.getRequestDispatcher("traderForm.jsp").forward(request, response);
        } else {
            if (method.equals("delete")) {
                SqlResult sqlResult = buyersTableService.deleteBuyerById(id);

                if (sqlResult.equals(SqlResult.DELETE_ERROR)) {
                    request.getSession().setAttribute("errorMessage",
                            "Before deleting an item with id " + id + " from the “Buyers” table, " +
                                    "delete all links to this item from the Sales table.");
                }
            }

            response.sendRedirect("/lab3_database_interaction_practice_war_exploded/buyers-servlet");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        String id = request.getParameter("id");
        request.setAttribute("method", method);
        request.setAttribute("id", id);

        if (method != null) {
            String buyerName = request.getParameter("traderName");
            String contactPhone = request.getParameter("contactPhone");
            String contactEmail = request.getParameter("contactEmail");

            Trader buyer = new Trader(
                    Integer.parseInt(id),
                    buyerName,
                    contactPhone,
                    contactEmail);

            if (method.equals("post")) {
                buyersTableService.addBuyer(buyer);
            } else if (method.equals("put")) {
                buyersTableService.updateBuyer(buyer);
            }
        }

        response.sendRedirect("/lab3_database_interaction_practice_war_exploded/buyers-servlet");
    }
}
