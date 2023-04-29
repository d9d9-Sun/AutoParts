package by.mad.autoparts.services;

import by.mad.autoparts.models.Product;

import java.util.List;

public interface ProductDaoService {
    List<Product> getAllProducts();
    void addNewProduct(Product product);
    void deleteProductById(long id);
}
