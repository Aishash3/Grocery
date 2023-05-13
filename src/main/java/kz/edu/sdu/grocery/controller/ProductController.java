package kz.edu.sdu.grocery.controller;

import kz.edu.sdu.grocery.model.entity.Product;
import kz.edu.sdu.grocery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public List<Product> getAll() {
        return productService.getAllProducts();
    }
    @GetMapping
    public Product getProductByName(@RequestParam(name = "product name") String productName){
        return productService.getProductByName(productName);
    }
    //Aisha
    //TODO - get all products by category name, id (2 distinct methods)
    //TODO - get all products whose price is higher, lower than specified one (2 methods)
    //Nurlan
    //TODO - return all products ordering by price(parameter - ASC/DESC)
    //TODO - return all products order by product name
    //TODO - return all products count, average price group by category id
    //TODO - return list of products by paging(size, page, orderBy - parameters)
}