package ru.job4j.serialization.json;

public class Product {
    private String model;
    private String colour;
    private double weight;

    public Product(String model, String colour, double weight) {
        this.model = model;
        this.colour = colour;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Product{" + "model='" + model + '\'' + ", colour='" + colour + '\''
                + ", weight=" + weight + '}';
    }
}
