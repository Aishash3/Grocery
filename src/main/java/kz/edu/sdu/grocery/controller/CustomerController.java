package kz.edu.sdu.grocery.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.edu.sdu.grocery.model.DTO.CustomerDTO;
import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/all")
    @ApiOperation(value = "get all the customers (supports paging)", response = List.class)
    public List<Customer> getAll(@ApiParam(example = "1", value = "Page's number:") @RequestParam(name = "page", required = false, defaultValue = "0") Long page,
                                 @ApiParam(example = "10", value = "Number of items per page:") @RequestParam(name = "size", required = false, defaultValue = Integer.MAX_VALUE + "") Long size,
                                 @ApiParam("Make sorting by column:") @RequestParam(name = "sort", required = false, defaultValue = "lastName") String sort) {
        return customerService.getAllCustomers(page, size, sort);
    }

    @GetMapping
    @ApiOperation(value = "Get the customers by his first name", response = List.class)
    public List<Customer> getByFirstName(@ApiParam(value = "Search by first name: ") @RequestParam(name = "first name") String firstName) {
        return customerService.getByFirstName(firstName);
    }

    @GetMapping("/{customerId}")
    public Customer getById(@PathVariable("customerId") Long id) {
        return customerService.getById(id);
    }
    @PostMapping
    public void createCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.createCustomer(customerDTO);
    }
}
