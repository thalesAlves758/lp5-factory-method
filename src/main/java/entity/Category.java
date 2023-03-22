package entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Category {
    private static Integer nextId = 1;
    public static ArrayList<Category> categories = new ArrayList<Category>();

    private Integer id;
    private String name;

    public Category(String name) {
        this.id = getNextId();
        this.name = name;
    }

    private static Integer getNextId() {
        return nextId++;
    }

    public static void reset() {
        categories = new ArrayList<Category>();
        nextId = 1;
    }
}
