package entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Product {
    public static ArrayList<Product> products = new ArrayList<Product>();

    private Integer id;
    private String name;
    private Double value;
}
