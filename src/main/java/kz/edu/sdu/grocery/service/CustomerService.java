package kz.edu.sdu.grocery.service;

import kz.edu.sdu.grocery.model.CustomerMapper;
import kz.edu.sdu.grocery.model.DTO.CustomerDTO;
import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.repository.CustomerRepository;
import kz.edu.sdu.grocery.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    private CustomerMapper mapper = CustomerMapper.MAPPER;

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> employees = customerRepository.findAll();
        return employees.stream().map(mapper::toCustomerDTO).toList();
    }

    public CustomerDTO getByFirstName(String firstName) {
        return mapper.toCustomerDTO(customerRepository.getByFirstName(firstName));
    }

    public void createCustomer(CustomerDTO customerDTO) {
        Customer customer = mapper.toCustomer(customerDTO);
        customer.setId(getLastId());
        Customer customer1 = customerRepository.save(customer);
    }

    public Customer updateCustomer(CustomerDTO customerDTO, Long id) {
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Such customer doesn't exist"));
        Customer updatedCustomer = mapper.toCustomer(customerDTO);
        updatedCustomer.setId(id);
        return customerRepository.save(updatedCustomer);
    }

    public Long getLastId() {
        return customerRepository.getMaxId();
    }

    public Double getCustomerOrdersSum(String firstName) {
        Customer customer = customerRepository.getByFirstName(firstName);
        Long id = customer.getId();
        List<Object[]> list = orderRepository.getCustomerOrdersWithPrice(id);
        double total = 0L;
        for (Object[] row : list) {
            Double price = (Double) row[0];
            Integer amount = (Integer) row[1];
            total += price*amount;
        }
        return total;
    }

    public List<Map<String, Object>> getCustomerOrdersList(String firstName) {
        Long custId = customerRepository.getByFirstName(firstName).getId();
        List<Object[]> results = orderRepository.getCustomerOrdersListById(custId);
        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] row : results) {
            String productName = (String) row[0];
            Integer quantity = (Integer) row[1];
            Long orderId = (Long) row[2];
            String date1;
            if (row[3] != null) {
                date1 = ((String) row[3]).substring(0, 10);
            } else {
                date1 = "not defined";
            }
            String statusName = row[4] == null ? "" : (String) row[4];

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

    public Customer getById(Long id) {
        return customerRepository.findById(id).get();
    }
}