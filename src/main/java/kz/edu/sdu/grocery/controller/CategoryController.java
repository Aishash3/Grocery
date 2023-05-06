package kz.edu.sdu.grocery.controller;

import kz.edu.sdu.grocery.model.entity.Category;
import kz.edu.sdu.grocery.repository.OrderRepository;
import kz.edu.sdu.grocery.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
//
//@org.springframework.web.bind.annotation.RestController
@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @GetMapping
    public Category getCategoryByName(@RequestParam(name = "category name") String categoryName){

//        System.out.println(results.get(0).);

        return categoryService.getCategoryByName(categoryName);
    }





}

