package com.example.lab3databaseinteractionpractice.Services;

import com.example.lab3databaseinteractionpractice.Models.Sale;
import com.example.lab3databaseinteractionpractice.Models.Trader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ListConverterService {
    public static <T> T getObjectByListString(List<String> list, T testObj) {
        if (testObj instanceof Trader) {
            testObj = (T) new Trader(
                    Integer.parseInt(list.get(0)),
                    list.get(1),
                    list.get(2),
                    list.get(3)
            );
        } else if (testObj instanceof Sale) {
            testObj = (T) new Sale(
                    Integer.parseInt(list.get(0)),
                    Integer.parseInt(list.get(1)),
                    Integer.parseInt(list.get(2)),
                    Long.parseLong(list.get(4)),
                    list.get(3),
                    LocalDate.parse(list.get(5))
            );
        }

        return testObj;
    }

    public static List<List<String>> getSalesListWhereIdReplacedByName(
            List<List<String>> salesList,
            SellersTableService sellersTableService,
            BuyersTableService buyersTableService) {
        List<List<String>> replacedSalesList = new ArrayList<>();
        for (List<String> sale : salesList) {
            String sellerId = sale.get(1);
            String buyerId = sale.get(2);

            Trader seller = sellersTableService.getSellerById(sellerId);
            Trader buyer = buyersTableService.getBuyerById(buyerId);

            sale.set(1, seller.getName());
            sale.set(2, buyer.getName());

            replacedSalesList.add(sale);
        }
        return replacedSalesList;
    }
}
