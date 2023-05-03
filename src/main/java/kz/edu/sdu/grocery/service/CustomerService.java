package kz.edu.sdu.grocery.service;

import kz.edu.sdu.grocery.model.entity.Customer;
import kz.edu.sdu.grocery.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(Long page, Long size, String sort) {
        PageRequest pageable = PageRequest.of(page.intValue(), Math.toIntExact(size), Sort.by(sort));
        Page<Customer> employees = customerRepository.findAll(pageable);
        return employees.stream().toList();
    }

    public List<Customer> getByFirstName(String firstName) {
        return customerRepository.getByFirstName(firstName);
    }

    public Customer getByEmail(String email) {
        return customerRepository.getByEmail(email);
    }

}
