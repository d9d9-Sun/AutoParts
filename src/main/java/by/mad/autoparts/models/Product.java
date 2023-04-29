package by.mad.autoparts.models;

import lombok.Data;

@Data
public class Product {
    private String vendor;
    private String name;
    private String description;
    private double price;
}