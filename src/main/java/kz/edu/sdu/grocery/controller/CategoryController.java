package kz.edu.sdu.grocery.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.edu.sdu.grocery.model.DTO.CategoryDTO;
import kz.edu.sdu.grocery.model.DTO.CustomerDTO;
import kz.edu.sdu.grocery.model.entity.Category;
import kz.edu.sdu.grocery.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping("/getByName")
    @ApiOperation("get category with specified name")
    public CategoryDTO getByName(@RequestParam(name = "name") String name) {
        return categoryService.getCategoryByName(name);
    }
    //@GetMapping
    //public Category getCategoryByName(@RequestParam(name = "category name") String categoryName){
    //    return categoryService.getCategoryByName(categoryName);
    //}
    //Aisha
    //TODO: add Category only by name (excluding id in params)
    @PostMapping
    @ApiOperation("Create a new category with name, id")
    public HttpEntity<String> createCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    //TODO: get number of total categories
    //@GetMapping("/getCategoryOrderTotal")
    //@ApiOperation("Get total sum of categories")
    //public Double getCategoriesSum(@RequestParam(name = "name") String name) {
    //    return categoryService.getCategorySum(name);
    //}

}
