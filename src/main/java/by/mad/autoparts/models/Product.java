package by.mad.autoparts.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_vendor")
    private String productVendor;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_description", columnDefinition = "text")
    private String productDescription;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "product_image_link")
    private String productImageLink;

    public Product(String productVendor, String productName, String productDescription, double productPrice) {
        this.productVendor = productVendor;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }
}