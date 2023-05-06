package kz.edu.sdu.grocery.controller;

import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.repository.OrderRepository;
import kz.edu.sdu.grocery.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    OrderRepository orderRepository;
    @GetMapping("/all")
    public List<Customer> getAll(@RequestParam(name = "page", required = false, defaultValue = "0") Long page,
                                 @RequestParam(name = "size", required = false, defaultValue = Integer.MAX_VALUE + "") Long size,
                                 @RequestParam(name = "sort", required = false, defaultValue = "lastName") String sort) {
        return customerService.getAllCustomers(page, size, sort);
    }

    @GetMapping
    public List<Map<String, Object>> getByFistName(@RequestParam(name = "fist name") String firstName) {
        List<Object[]> results = orderRepository.returnAll();
        List<Map<String, Object>> response = new ArrayList<>();
        for(Object[] row : results) {
            Long customerId = (Long) row[0];
            String firstName1 = (String) row[1];
            if (!firstName1.equals(firstName)){
                continue;
            }
            String lastName = (String) row[2];
            Long productId = (Long) row[3];
            Integer quantity = (Integer) row[4];
            Long orderId = (Long) row[5];
            String date1;
            if (row[6]!=null){
                date1=((String)row[6]).substring(0,10);
            }else{
                date1="not found";
            }
            String statusName = (String) row[7];

            Map<String, Object> result = new LinkedHashMap<>();
            result.put("customerId", customerId);
            result.put("firstName", firstName1);
            result.put("lastName", lastName);
            result.put("productId", productId);
            result.put("quantity", quantity);
            result.put("orderId", orderId);
            result.put("statusName", statusName);
            result.put("statusDate", date1);

            response.add(result);
        }

        return response;
    }


//    @GetMapping
//    public Customer getByEmail(@RequestParam(name = "email") String email) {
//        return customerService.getByEmail(email);
//    }

}
