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

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public String getWeight() {
        return Double.toString(weight);
    }

    @Override
    public String toString() {
        return "Product{" + "model='" + model + '\'' + ", colour='" + colour + '\''
                + ", weight=" + weight + '}';
    }
}
