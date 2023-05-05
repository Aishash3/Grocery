package kz.edu.sdu.grocery.controller;

import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//
//@org.springframework.web.bind.annotation.RestController
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/all")
    public List<Customer> getAll(@RequestParam(name = "page", required = false, defaultValue = "0") Long page,
                                 @RequestParam(name = "size", required = false, defaultValue = Integer.MAX_VALUE + "") Long size,
                                 @RequestParam(name = "sort", required = false, defaultValue = "lastName") String sort) {
        return customerService.getAllCustomers(page, size, sort);
    }

    @GetMapping
    public List<Customer> getByFirstName(@RequestParam(name = "fist name") String firstName) {
        return customerService.getByFirstName(firstName);
    }

//    public Customer getByEmail(@RequestParam(name = "email") String email) {
//        return customerService.getByEmail(email);
//    }

}
