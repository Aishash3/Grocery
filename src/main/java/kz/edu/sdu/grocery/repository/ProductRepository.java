package kz.edu.sdu.grocery.repository;

import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.model.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductByName(String productName);


    @Query("SELECT p.id, p.name, p.price, p.category FROM Product AS p Order by p.price DESC")
    List<Object[]> getProductsSortedByPrice();

    @Query("SELECT p.id, p.name, p.price, p.category FROM Product AS p Order by p.name")
    List<Object[]> getProductsSortedByName();

    @Query("SELECT c.name, COUNT(p.name), AVG(p.price)\n" +
            "FROM Product AS p\n" +
            "JOIN Category AS c ON p.category = c.id\n" +
            "GROUP BY p.category")
    List<Object[]> getProductsCategoryInfo();

    @Query("SELECT p.id, p.name, p.price, p.category FROM Product as p where p.price>:price")
    List<Object[]> getProductHigherPrice(@Param("price") Double price);


    @Query("SELECT p.id, p.name, p.price, p.category FROM Product as p where p.price<:price")
    List<Object[]> getProductLowerPrice(@Param("price") Double price);



    List<Product> findAllByPrice(double price, Pageable pageable);

}