package by.mad.autoparts.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private long id;
    private String vendor;
    private String name;
    private String description;
    private double price;
}