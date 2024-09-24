package com.example.lab3databaseinteractionpractice.Services;

import com.example.lab3databaseinteractionpractice.Models.Buyer;
import com.example.lab3databaseinteractionpractice.Models.Seller;

import java.util.List;

public class ListConverterService {
    public static <T> T getObjectByListString(List<String> list, T testObj) {
        if (testObj instanceof Seller) {
            testObj = (T) new Seller(
                    Integer.parseInt(list.get(0)),
                    list.get(1),
                    list.get(2),
                    list.get(3)
            );
        } else if (testObj instanceof Buyer) {
            testObj = (T) new Buyer(
                    Integer.parseInt(list.get(0)),
                    list.get(1),
                    list.get(2),
                    list.get(3)
            );
        }

        return testObj;
    }
}
