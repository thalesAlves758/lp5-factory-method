package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Category {
    public static ArrayList<Category> categories = new ArrayList<Category>();

    private int id;
    private String name;
}
