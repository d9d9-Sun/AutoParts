package by.mad.autoparts.services;

import by.mad.autoparts.models.Product;

import java.util.List;

public interface ProductDaoService {
    List<Product> getAllProducts();
    void addNewProduct(Product product);
    void deleteProductById(Long id);
    Product findProductById(Long id);
    void updateProduct(Product product);

    void deleteProductImage(Product product);
}
