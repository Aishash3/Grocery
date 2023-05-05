package kz.edu.sdu.grocery.repository;

import kz.edu.sdu.grocery.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> getByFirstName(String firstName);
    Customer getByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT MAX(ID) FROM CUSTOMER")
    Long getMaxId();
}
