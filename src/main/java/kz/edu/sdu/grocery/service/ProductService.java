package kz.edu.sdu.grocery.service;

import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.model.entity.Product;
import kz.edu.sdu.grocery.repository.CustomerRepository;
import kz.edu.sdu.grocery.repository.OrderRepository;
import kz.edu.sdu.grocery.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public List<Object[]> getProductsSortedByPrice(){ return productRepository.getProductsSortedByPrice();}


    public List<Object[]> getProductsSortedByName(){ return productRepository.getProductsSortedByName();}


    public List<Map<String, Object>> getProductsCategoryInfo(){
        List<Object[]> results = productRepository.getProductsCategoryInfo();
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] row : results) {
            String categoryName = (String) row[0];
            Long quantity = (Long) row[1];
            Double averagePrice = (Double) row[2];

            Map<String, Object> result = new LinkedHashMap<>();
            result.put("categoryName", categoryName);
            result.put("quantity", quantity);
            result.put("averagePrice", averagePrice);

            response.add(result);
        }
        return response;

    }

    public List<Product> getAllProducts(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("name").descending());

        Page<Product> pagedResult = productRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Product>();
        }
    }

}