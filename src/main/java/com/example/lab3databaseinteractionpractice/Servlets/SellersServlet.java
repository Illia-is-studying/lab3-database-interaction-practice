package com.example.lab3databaseinteractionpractice.Servlets;

import com.example.lab3databaseinteractionpractice.Models.SqlResult;
import com.example.lab3databaseinteractionpractice.Models.Trader;
import com.example.lab3databaseinteractionpractice.Services.SellersTableService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "sellersServlet", value = "/sellers-servlet")
public class SellersServlet extends HttpServlet {
    private SellersTableService sellersTableService;

    @Override
    public void init() throws ServletException {
        sellersTableService = new SellersTableService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        String id = request.getParameter("id");

        request.setAttribute("method", method);

        if (method == null || method.equals("get")) {
            sellersTableService.setInitialQueryGetAll();
            List<List<String>> sellerRows = sellersTableService.getAllSellersAfterSettingQuery();

            request.setAttribute("tableName", "Sellers Table");
            request.setAttribute("servletName", "sellers-servlet");
            request.setAttribute("rowsAttr", sellerRows);
            request.setAttribute("headersAttr", sellersTableService.getHeaders());
            request.getRequestDispatcher("traders.jsp").forward(request, response);
        } else if (method.equals("post") || method.equals("put")) {
            Trader seller = new Trader();

            if (method.equals("put")) {
                seller = sellersTableService.getSellerById(id);

                if (seller.getId() < 1) {
                    request.setAttribute("method", "post");
                }
            }

            request.setAttribute("formName", "Seller");
            request.setAttribute("trader", seller);
            request.getRequestDispatcher("traderForm.jsp").forward(request, response);
        } else {
            if (method.equals("delete")) {
                SqlResult sqlResult = sellersTableService.deleteSellerById(id);

                if (sqlResult.equals(SqlResult.DELETE_ERROR)) {
                    request.getSession().setAttribute("errorMessage",
                            "Before deleting an item with id " + id + " from the “Sellers” table, " +
                                    "delete all links to this item from the Sales table.");

                }
            }

            response.sendRedirect("/lab3_database_interaction_practice_war_exploded/sellers-servlet");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        String id = request.getParameter("id");
        request.setAttribute("method", method);
        request.setAttribute("id", id);

        if (method != null) {
            String sellerName = request.getParameter("traderName");
            String contactPhone = request.getParameter("contactPhone");
            String contactEmail = request.getParameter("contactEmail");

            Trader seller = new Trader(
                    Integer.parseInt(id),
                    sellerName,
                    contactPhone,
                    contactEmail);

            if (method.equals("post")) {
                sellersTableService.addSeller(seller);
            } else if (method.equals("put")) {
                sellersTableService.updateSeller(seller);
            }
        }

        response.sendRedirect("/lab3_database_interaction_practice_war_exploded/sellers-servlet");
    }
}
