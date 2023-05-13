package kz.edu.sdu.grocery.service;

import kz.edu.sdu.grocery.model.CustomerMapper;
import kz.edu.sdu.grocery.model.DTO.CustomerDTO;
import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    private CustomerMapper mapper = CustomerMapper.MAPPER;

    public List<CustomerDTO> getAllCustomers() {

        List<Customer> employees = customerRepository.findAll();
        return employees.stream().map(mapper::toCustomerDTO).toList();
    }

    public List<CustomerDTO> getByFirstName(String firstName) {
        return customerRepository.getByFirstName(firstName).stream().map(mapper::toCustomerDTO).toList();
    }

    public void createCustomer(CustomerDTO customerDTO) {
        Customer customer = mapper.toCustomer(customerDTO);
        customer.setId(getLastId());
        Customer customer1 = customerRepository.save(customer);
    }
    public Customer updateCustomer(CustomerDTO customerDTO, Long id){
        Customer customer = customerRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Such customer doesn't exist"));
        Customer updatedCustomer = mapper.toCustomer(customerDTO);
        updatedCustomer.setId(id);
        return customerRepository.save(updatedCustomer);
    }
    public void deleteCustomerByName(String firstName){
        customerRepository.removeByFirstName(firstName);
    }
    public Long getLastId() {
        return customerRepository.getMaxId();
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id).get();
    }
}