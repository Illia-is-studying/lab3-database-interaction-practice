package com.example.lab3databaseinteractionpractice.Services;

import com.example.lab3databaseinteractionpractice.Models.Trader;

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
        }

        return testObj;
    }
}
