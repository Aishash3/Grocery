package kz.edu.sdu.grocery.service;

import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.model.entity.Category;
import kz.edu.sdu.grocery.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category getCategoryByName(String categoryName) {
        return categoryRepository.getCategoryByName(categoryName);
    }
}

