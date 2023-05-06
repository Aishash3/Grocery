package kz.edu.sdu.grocery.repository;

import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductByName(String productName);
}