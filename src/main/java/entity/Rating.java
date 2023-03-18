package entity;

public class Rating {
    private int id;
    private Double starValue;
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getStarValue() {
        return starValue;
    }

    public void setStarValue(Double starValue) {
        this.starValue = starValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
