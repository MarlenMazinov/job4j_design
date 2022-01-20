package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

        JSONObject jsonProduct = new JSONObject();
        jsonProduct.put("model", "M23XL");
        jsonProduct.put("colour", "White");
        jsonProduct.put("weight", "12.0");
        List<String> list = new ArrayList<>();
        list.add("Moscow1");
        list.add("Moscow2");
        list.add("Novgorod1");
        list.add("Krasnodar1");
        JSONArray jsonWarehouses = new JSONArray(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", card.getId());
        jsonObject.put("name", card.getName());
        jsonObject.put("isPresent", card.getIsPresent());
        jsonObject.put("product", jsonProduct);
        jsonObject.put("warehouses", jsonWarehouses);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(card).toString());
    }
}