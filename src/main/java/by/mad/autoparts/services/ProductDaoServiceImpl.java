package by.mad.autoparts.services;

import by.mad.autoparts.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDaoServiceImpl implements ProductDaoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = jdbcTemplate.query("SELECT * FROM products", (ResultSet rs, int rownum) -> {
            Product product = new Product();

            product.setProductId(rs.getLong("product_id"));
            product.setProductVendor(rs.getString("product_vendor"));
            product.setProductName(rs.getString("product_name"));
            product.setProductDescription(rs.getString("product_description"));
            product.setProductPrice(rs.getDouble("product_price"));

            return product;
        });
        return products;
    }

    @Override
    public void addNewProduct(Product product) {
        productList.add(product);

    }

    @Override
    public void deleteProductById(Long id) {
        productList.removeIf(productList -> productList.getProductId() == id);
    }

}
