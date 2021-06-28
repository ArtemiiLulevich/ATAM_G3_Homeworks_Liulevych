package Homework_3.entities;

import static java.lang.String.format;

public class Item{

    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Item setPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return format("{Name: %s, Price: %s}\n", this.name, this.price);
    }

}
