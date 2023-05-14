package kz.edu.sdu.grocery.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.edu.sdu.grocery.model.DTO.CustomerDTO;
import kz.edu.sdu.grocery.repository.OrderRepository;
import kz.edu.sdu.grocery.service.CustomerService;
import kz.edu.sdu.grocery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;

    @GetMapping("/all")
    @ApiOperation("get all customers")
    public List<CustomerDTO> getAll() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/getByFirstName")
    @ApiOperation("get customers with specified name")
    public CustomerDTO getByFirstName(@RequestParam(name = "first name") String firstName) {
        return customerService.getByFirstName(firstName);
    }

    @PostMapping
    @ApiOperation("Create a new customer with first name, last name, email, phone")
    public HttpEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Update customer data by id")
    public HttpEntity updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDTO customerDTO) {
        try {
            return ResponseEntity.ok(customerService.updateCustomer(customerDTO, id));
        } catch (Exception e) {
            return new ResponseEntity<>("Customer with such ID doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCustomerOrders")
    @ApiOperation("Get all orders of customer specified by name")
    public List<Map<String, Object>> getOrdersByFirstName(@RequestParam(name = "first name") String firstName) {
        return customerService.getCustomerOrdersList(firstName);
    }

    @GetMapping("/getCustomersOrderTotal")
    @ApiOperation("Get total sum of customer's order")
    public Double getOrdersSum(@RequestParam(name = "first name") String firstName) {
        return customerService.getCustomerOrdersSum(firstName);
    }
    @GetMapping("/getOrdersByStatus")
    public List<Object[]> getCustomerOrdersByStatus(@RequestParam(name = "customer name") String firstName,
                                          @ApiParam("Status id:1 - under consideration, 2 - accepted, 3 - rejected")
                                          @RequestParam(name = "status id") String statusId){
        return productService.getOrdersByStatusIdAndCustomerName(firstName, Long.parseLong(statusId));
    }
    //TODO - list of orders whose status is equal to specified one (product_name, customer_name, amount)
}
