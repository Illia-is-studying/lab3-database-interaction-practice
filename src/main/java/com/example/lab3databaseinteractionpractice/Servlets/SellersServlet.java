package com.example.lab3databaseinteractionpractice.Servlets;

import com.example.lab3databaseinteractionpractice.Models.Seller;
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
            sellersTableService.setInitialQuery();
            List<List<String>> sellerRows = sellersTableService.getAllSellersAfterSettingQuery();

            request.setAttribute("tableName", "Sellers Table");
            request.setAttribute("servletName", "sellers-servlet");
            request.setAttribute("rowsAttr", sellerRows);
            request.setAttribute("headersAttr", sellersTableService.getHeaders());
            request.getRequestDispatcher("table.jsp").forward(request, response);
        } else if (method.equals("post")) {
            Seller seller = new Seller();
            request.setAttribute("seller", seller);
            request.getRequestDispatcher("sellerForm.jsp").forward(request, response);
        } else if (method.equals("put")) {
            Seller seller = sellersTableService.getSellerById(id);

            request.setAttribute("seller", seller);
            if (seller.getSellerID() < 1) {
                request.setAttribute("method", "post");
            }

            request.getRequestDispatcher("sellerForm.jsp").forward(request, response);
        } else {
            if (method.equals("delete")) {
                sellersTableService.deleteSellerById(id);
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
            String sellerName = request.getParameter("sellerName");
            String contactPhone = request.getParameter("contactPhone");
            String contactEmail = request.getParameter("contactEmail");

            Seller seller = new Seller(
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
