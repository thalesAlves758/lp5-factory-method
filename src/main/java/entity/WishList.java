package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class WishList {
    public static ArrayList<WishList> wishLists = new ArrayList<WishList>();

    private Integer id;
    private String title;
    private String description;
}
