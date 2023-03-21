package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Rating {
    public static ArrayList<Rating> ratings = new ArrayList<Rating>();

    private int id;
    private Double starValue;
    private String comment;
}
