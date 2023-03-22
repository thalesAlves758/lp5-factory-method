package entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class WishList {
    private static Integer nextId = 1;
    public static ArrayList<WishList> wishLists = new ArrayList<WishList>();

    private Integer id;
    private String title;
    private String description;

    public WishList(String title, String description) {
        this.id = getNextId();
        this.title = title;
        this.description = description;
    }

    private static Integer getNextId() {
        return nextId++;
    }

    public static void reset() {
        wishLists = new ArrayList<WishList>();
        nextId = 1;
    }
}
