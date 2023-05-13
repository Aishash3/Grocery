package kz.edu.sdu.grocery.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kz.edu.sdu.grocery.model.DTO.CustomerDTO;
import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.repository.OrderRepository;
import kz.edu.sdu.grocery.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/all")
    @ApiOperation("get all customers")
    public List<CustomerDTO> getAll() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/getByFirstName")
    @ApiOperation("get customers with specified name")
    public List<CustomerDTO> getByFirstName(@RequestParam(name = "first name") String firstName) {
        return customerService.getByFirstName(firstName);
    }

    @PostMapping
    @ApiOperation("Create a new customer with first name, last name, email, phone")
    public HttpEntity<String> createCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    @ApiOperation("update customer data by id")
    public HttpEntity updateCustomer(@ApiParam("customer id") @PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        try {
            return ResponseEntity.ok(customerService.updateCustomer(customerDTO, id));
        } catch (Exception e) {
            return new ResponseEntity<>("Customer with such ID doesn't exist", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCustomerOrders")
    @ApiOperation("get all orders of customer specified by name")
    public List<Map<String, Object>> getOrdersByFirstName(@RequestParam(name = "first name") String firstName) {
        List<Object[]> results = orderRepository.returnAll();
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] row : results) {
            String firstName1 = (String) row[0];
            if (!firstName1.equals(firstName)) {
                continue;
            }
            String productName = (String) row[1];
            Integer quantity = (Integer) row[2];
            Long orderId = (Long) row[3];
            String date1;
            if (row[4] != null) {
                date1 = ((String) row[4]).substring(0, 10);
            } else {
                date1 = "not defined";
            }
            String statusName = (String) row[5];

            Map<String, Object> result = new LinkedHashMap<>();
            result.put("productName", productName);
            result.put("quantity", quantity);
            result.put("orderId", orderId);
            result.put("statusName", statusName);
            result.put("statusDate", date1);

            response.add(result);
        }
        return response;
    }

    //TODO - сумма заказа покупателя
    //TODO - total amount of each product sold in orders
    //TODO - list of orders whose status is equal to specified one (product_name, customer_name, amount)
}
