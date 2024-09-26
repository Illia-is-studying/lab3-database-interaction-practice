package com.example.lab3databaseinteractionpractice.Servlets;

import com.example.lab3databaseinteractionpractice.Models.Sale;
import com.example.lab3databaseinteractionpractice.Services.BuyersTableService;
import com.example.lab3databaseinteractionpractice.Services.ListConverterService;
import com.example.lab3databaseinteractionpractice.Services.SalesTableService;
import com.example.lab3databaseinteractionpractice.Services.SellersTableService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "salesServlet", value = "/sales-servlet")
public class SalesServlet extends HttpServlet {
    private SalesTableService salesTableService;
    private SellersTableService sellerTableService;
    private BuyersTableService buyersTableService;

    @Override
    public void init() throws ServletException {
        salesTableService = new SalesTableService();
        sellerTableService = new SellersTableService();
        buyersTableService = new BuyersTableService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        String id = request.getParameter("id");

        request.setAttribute("method", method);

        sellerTableService.setInitialQueryGetIdAndName();
        List<List<String>> sellersLineList = sellerTableService.getAllSellersAfterSettingQuery();

        buyersTableService.setInitialQueryGetIdAndName();
        List<List<String>> buyersLineList = buyersTableService.getAllBuyersAfterSettingQuery();

        Map<Integer, String> sellersMap = sellersLineList.stream()
                .collect(Collectors.toMap(
                        entry -> Integer.parseInt(entry.get(0)),
                        entry -> entry.get(1)
                ));

        Map<Integer, String> buyersMap = buyersLineList.stream()
                .collect(Collectors.toMap(
                        entry -> Integer.parseInt(entry.get(0)),
                        entry -> entry.get(1)
                ));

        request.setAttribute("sellersMap", sellersMap);
        request.setAttribute("buyersMap", buyersMap);

        if (method == null || method.equals("get")) {
            String saleDate = request.getParameter("saleDate");
            String saleLowerRangeDate = request.getParameter("saleLowerRangeDate");
            String saleUpperRangeDate = request.getParameter("saleUpperRangeDate");
            String buyerID = request.getParameter("buyerID");
            String sellerID = request.getParameter("sellerID");

            salesTableService.setInitialQueryGetAll();

            if (saleDate != null && !saleDate.isEmpty()) {
                salesTableService.setSaleDateCondition(saleDate);
            } else if (saleLowerRangeDate != null && !saleLowerRangeDate.isEmpty() &&
                    saleUpperRangeDate != null && !saleUpperRangeDate.isEmpty()) {
                salesTableService.setSaleDateRangeCondition(saleLowerRangeDate, saleUpperRangeDate);
            }
            if (buyerID != null && !buyerID.equalsIgnoreCase("all")) {
                salesTableService.setBuyerIdCondition(buyerID);
            }
            if (sellerID != null && !sellerID.equalsIgnoreCase("all")) {
                salesTableService.setSellerIdCondition(sellerID);
            }

            List<List<String>> saleRows = salesTableService.getAllSalesAfterSettingQuery();
            saleRows = ListConverterService
                    .getSalesListWhereIdReplacedByName(saleRows, sellerTableService, buyersTableService);

            request.setAttribute("tableName", "Sales Table");
            request.setAttribute("servletName", "sales-servlet");
            request.setAttribute("rowsAttr", saleRows);
            request.setAttribute("headersAttr", salesTableService.getHeaders());
            request.getRequestDispatcher("sales.jsp").forward(request, response);
        } else if (method.equals("post") || method.equals("put")) {
            Sale sale = new Sale();

            if (method.equals("put")) {
                sale = salesTableService.getSaleById(id);

                if (sale.getSaleID() < 1) {
                    request.setAttribute("method", "post");
                }
            }

            request.setAttribute("formName", "Sale");
            request.setAttribute("sale", sale);
            request.getRequestDispatcher("saleForm.jsp").forward(request, response);
        } else {
            if (method.equals("delete")) {
                salesTableService.deleteSalesById(id);
            }

            response.sendRedirect("/lab3_database_interaction_practice_war_exploded/sales-servlet");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("method");
        String id = request.getParameter("id");
        request.setAttribute("method", method);
        request.setAttribute("id", id);

        if (method != null) {
            String sellerID = request.getParameter("sellerID");
            String buyerID = request.getParameter("buyerID");
            String sellingPrice = request.getParameter("sellingPrice");
            String productName = request.getParameter("productName");
            String saleDate = request.getParameter("saleDate");

            Sale sale = new Sale(
                    Integer.parseInt(id),
                    Integer.parseInt(sellerID),
                    Integer.parseInt(buyerID),
                    Long.parseLong(sellingPrice),
                    productName,
                    LocalDate.parse(saleDate));

            if (method.equals("post")) {
                salesTableService.addSale(sale);
            } else if (method.equals("put")) {
                salesTableService.updateSale(sale);
            }
        }

        response.sendRedirect("/lab3_database_interaction_practice_war_exploded/sales-servlet");
    }
}
