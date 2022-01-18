package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final ProductCard card = new ProductCard(15, "ProductName", true,
                new Product("M23XL", "White", 12.0),
                new String[]{"Moscow1", "Moscow2", "Novgorod1", "Krasnodar1"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(card));
        final String productCardJson =
                "{"
                        + "\"id\":15,"
                        + "\"name\":ProductName,"
                        + "\"isPresent\":true,"
                        + "\"product\":"
                        + "{"
                        + "\"model\":\"M23XL\","
                        + "\"colour\":\"White\","
                        + "\"weight\":\"12.0\""
                        + "},"
                        + "\"warehouses\":"
                        + "[\"Moscow1\",\"Moscow2\",\"Novgorod1\",\"Krasnodar1\"]"
                        + "}";
        final ProductCard productCardMod = gson.fromJson(productCardJson, ProductCard.class);
        System.out.println(productCardMod);
    }
}