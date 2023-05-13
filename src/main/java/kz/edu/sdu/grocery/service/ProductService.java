package kz.edu.sdu.grocery.service;

import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.model.entity.Product;
import kz.edu.sdu.grocery.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

//    public List<Customer> getAllCustomers(Long page, Long size, String sort) {
//        PageRequest pageable = PageRequest.of(page.intValue(), Math.toIntExact(size), Sort.by(sort));
//        Page<Customer> employees = customerRepository.findAll(pageable);
//        return employees.stream().toList();
//    }

    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products;
    }
    public Product getProductByName(String productName) {
        return productRepository.getProductByName(productName);
    }
}