package by.mad.autoparts.services;

import by.mad.autoparts.models.Product;
import by.mad.autoparts.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDaoServiceImpl implements ProductDaoService {

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


}
