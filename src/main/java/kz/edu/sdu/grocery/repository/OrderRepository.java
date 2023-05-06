package kz.edu.sdu.grocery.repository;

import kz.edu.sdu.grocery.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT c.id AS CUSTOMER_ID, c.firstName, c.lastName, p.name, o.quantity, o.id AS ORDER_ID, oh.modify_date, s.name AS STATUS_NAME FROM Orders o\n" +
            "LEFT JOIN Customer c ON o.customer.id = c.id\n" +
            "LEFT JOIN Product p ON p.id = o.product.id\n" +
            "LEFT JOIN Orders_history oh ON oh.order.id = o.id\n" +
            "LEFT JOIN Status s ON s.id = oh.statusId")
    List<Object[]> returnAll();

}
