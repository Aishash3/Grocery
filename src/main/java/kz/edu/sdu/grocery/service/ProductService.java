package kz.edu.sdu.grocery.service;

import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.model.entity.Product;
import kz.edu.sdu.grocery.repository.CustomerRepository;
import kz.edu.sdu.grocery.repository.OrderRepository;
import kz.edu.sdu.grocery.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    OrderRepository orderRepository;
    public List<Product> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products;
    }
    public Map<String, Long> getProductAmounts(){
        Map<String, Long> map = new LinkedHashMap<>();
        List<Object[]> result = orderRepository.getProductsAmount();
        for(Object[] row: result) {
            Long sum = (Long) row[0];
            map.put((String) row[1], sum);
        }
        return map;
    }
    public List<Object[]> getOrdersByStatusIdAndCustomerName(String customerName, Long statusId){
        Customer customer = customerRepository.getByFirstName(customerName);
        return orderRepository.getOrdersByCustomerIdAndStatusId(customer.getId(), statusId);
    }
    public Product getProductByName(String productName) {
        return productRepository.getProductByName(productName);
    }
}