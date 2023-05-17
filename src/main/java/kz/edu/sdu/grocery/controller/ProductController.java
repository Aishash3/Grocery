package kz.edu.sdu.grocery.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kz.edu.sdu.grocery.model.entity.Product;
import kz.edu.sdu.grocery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    @GetMapping("/soldAmount")
    @ApiOperation("Total amount of each product sold in orders")
    public Map<String, Long> getProductsAmount(){
        return productService.getProductAmounts();
    }
    //Aisha
    //TODO - get all products by category name, id (2 distinct methods)

    //TODO - get all products whose price is higher, lower than specified one (2 methods)
    @GetMapping("/findByPriceHigher")
    public List<Object[]> getProductsHigherPrice(@RequestParam(name = "price") Double productPrice){
        return productService.getProductsHigherPrice(productPrice);
    }


    @GetMapping("/findByPriceLower")
    public List<Object[]> getProductsLowerPrice(@RequestParam(name = "price") Double productPrice){
        return productService.getProductsLowerPrice(productPrice);
    }



    //Nurlan
    //TODO - return all products ordering by price(parameter - ASC/DESC)

    @GetMapping("/sortByPrice")
    public List<Object[]> getProductsSortedByPrice(){
        return productService.getProductsSortedByPrice();
    }

    //TODO - return all products order by product name
    @GetMapping("/sortByName")
    public List<Object[]> getProductsSortedByName(){
        return productService.getProductsSortedByName();
    }

    //TODO - return all products count, average price group by category id

    @GetMapping("/categoryStatistics")
    public List<Map<String, Object>> getProductsCategoryInfo(){
        return productService.getProductsCategoryInfo();
    }
    //TODO - return list of products by paging(size, page, orderBy - parameters)

    @GetMapping("/getPagingInfo")
    public List<Product> findAllProductsByPaging(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
            ){
        List<Product> products = productService.getAllProducts(page, size, sortBy);
        return products;
    }

}