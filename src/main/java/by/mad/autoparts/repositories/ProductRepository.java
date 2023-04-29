package by.mad.autoparts.repositories;

import by.mad.autoparts.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
