package kz.edu.sdu.grocery.service;

import kz.edu.sdu.grocery.model.CategoryMapper;
import kz.edu.sdu.grocery.model.DTO.CategoryDTO;
import kz.edu.sdu.grocery.model.DTO.CustomerDTO;
import kz.edu.sdu.grocery.model.entity.Category;
import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.repository.CategoryRepository;
import kz.edu.sdu.grocery.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    private OrderRepository orderRepository;
    private CategoryMapper mapper = CategoryMapper.MAPPER;
    public List<CategoryDTO> getAllCategory() {
        List<Category> employees = categoryRepository.findAll();
        return employees.stream().map(mapper::toCategoryDTO).toList();
    }

    //public Category getCategoryByName(String categoryName) {
      //  return categoryRepository.getCategoryByName(categoryName);

    //}
    public CategoryDTO getCategoryByName(String name) {
        return mapper.toCategoryDTO(categoryRepository.getCategoryByName(name));
    }

    public void createCategory(CategoryDTO categoryDTO) {
        Category category = mapper.toCategory(categoryDTO);
        category.setId(getLastId());
        Category category1 = categoryRepository.save(category);
    }

    public Long getLastId() {
        return categoryRepository.getMaxId();
    }

    //public Double getCategorySum(String name) {
        //Category category = categoryRepository.getCategoryByName(name);
        //Long id = category.getId();
        //List<Object[]> list = categoryRepository.getMaxId(id);
        //double total = 0L;
        //for (Object[] row : list) {
       //     Double id = (Double) row[0];
        //    total += id;
        //}
        //return total;
    //}

    //public Customer updateCustomer(CustomerDTO customerDTO, Long id) {
    //    Customer customer = customerRepository.findById(id).
    //            orElseThrow(() -> new RuntimeException("Such customer doesn't exist"));
    //    Customer updatedCustomer = mapper.toCustomer(customerDTO);
    //    updatedCustomer.setId(id);
    //   return customerRepository.save(updatedCustomer);
    //}


}

