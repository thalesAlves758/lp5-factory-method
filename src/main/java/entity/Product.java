package entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Product {
    private static Integer nextId = 1;
    public static ArrayList<Product> products = new ArrayList<Product>();

    private Integer id;
    private String name;
    private Double value;

    public Product(String name, Double value) {
        this.id = getNextId();
        this.name = name;
        this.value = value;
    }

    private static Integer getNextId() {
        return nextId++;
    }

    public static void reset() {
        products = new ArrayList<Product>();
        nextId = 1;
    }
}
