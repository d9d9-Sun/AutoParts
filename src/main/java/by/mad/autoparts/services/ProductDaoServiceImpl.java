package by.mad.autoparts.services;

import by.mad.autoparts.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDaoServiceImpl implements ProductDaoService {
    private static List<Product> productList = new ArrayList<>();
    private static long id = 0L;

    static {
        productList.add((new Product(++id, "TRW", "Тормозные колодки", "Подходит для Volvo XC90", 250.00)));
        productList.add((new Product(++id, "Patron", "Насос гидроусилителя", "Подходит для Volvo XC90", 180.00)));
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public void addNewProduct(Product product) {
        product.setId(++id);
        productList.add(product);
    }

    @Override
    public void deleteProductById(long id) {
        productList.removeIf(productList -> productList.getId() == id);
    }

}
