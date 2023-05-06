package kz.edu.sdu.grocery.controller;

import kz.edu.sdu.grocery.model.entity.Category;
import kz.edu.sdu.grocery.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        return categoryService.getCategoryByName(categoryName);
    }




}

