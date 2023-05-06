package kz.edu.sdu.grocery.controller;

import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.model.entity.Product;
import kz.edu.sdu.grocery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//
//@org.springframework.web.bind.annotation.RestController
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping("/all")
    public List<Product> getAll(@RequestParam(name = "page", required = false, defaultValue = "0") Long page,
                                 @RequestParam(name = "size", required = false, defaultValue = Integer.MAX_VALUE + "") Long size,
                                 @RequestParam(name = "sort", required = false, defaultValue = "name") String sort) {
        return productService.getAllProducts(page, size, sort);
    }
    @GetMapping
    public Product getProductByName(@RequestParam(name = "product name") String productName){
        return productService.getProductByName(productName);
    }
}