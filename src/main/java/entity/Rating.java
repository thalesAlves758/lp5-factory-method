package entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Rating {
    private static Integer nextId = 1;
    public static ArrayList<Rating> ratings = new ArrayList<Rating>();

    private Integer id;
    private Double starValue;
    private String comment;

    public Rating(Double starValue, String comment) {
        this.id = getNextId();
        this.starValue = starValue;
        this.comment = comment;
    }

    private static Integer getNextId() {
        return nextId++;
    }

    public static void reset() {
        ratings = new ArrayList<Rating>();
        nextId = 1;
    }
}
