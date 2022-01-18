package ru.job4j.serialization.json;

import java.util.Arrays;

public class ProductCard {
    private int id;
    private String name;
    private boolean isPresent;
    private Product product;
    private String[] warehouses;

    public ProductCard(int id, String name, boolean isPresent, Product product, String[] warehouses) {
        this.id = id;
        this.name = name;
        this.isPresent = isPresent;
        this.product = product;
        this.warehouses = warehouses;
    }

    @Override
    public String toString() {
        return "ProductCard{" + "id=" + id + ", name='" + name + '\''
                + ", isPresent=" + isPresent + ", product=" + product
                + ", warehouses=" + Arrays.toString(warehouses) + '}';
    }
}
