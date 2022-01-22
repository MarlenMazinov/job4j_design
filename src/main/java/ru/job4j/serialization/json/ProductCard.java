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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getIsPresent() {
        return isPresent;
    }

    public String[] getWarehouses() {
        return warehouses;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "ProductCard{" + "id=" + id + ", name='" + name + '\''
                + ", isPresent=" + isPresent + ", product=" + product
                + ", warehouses=" + Arrays.toString(warehouses) + '}';
    }
}
