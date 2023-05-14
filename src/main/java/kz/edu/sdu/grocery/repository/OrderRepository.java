package kz.edu.sdu.grocery.repository;

import kz.edu.sdu.grocery.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query("SELECT p.name, o.quantity, o.id AS ORDER_ID, oh.modify_date, s.name AS STATUS_NAME FROM Orders o\n" +
            "LEFT JOIN Customer c ON o.customer.id = c.id\n" +
            "LEFT JOIN Product p ON p.id = o.product.id\n" +
            "LEFT JOIN Orders_history oh ON oh.order.id = o.id\n" +
            "LEFT JOIN Status s ON s.id = oh.statusId\n" +
            "where c.id = :customerId")
    List<Object[]> getCustomerOrdersListById(@Param("customerId") Long id);

    @Query("Select p.price, o.quantity from Orders o\n" +
            "LEFT JOIN Product p ON p.id = o.product.id\n" +
            "WHERE o.customer.id = :custId")
    List<Object[]> getCustomerOrdersWithPrice(@Param("custId") Long id);

    @Query(value = "SELECT sum(o.quantity), p.name from Orders AS o " +
            "JOIN Product AS p ON p.id = o.product.id " +
            "GROUP BY o.product.id " +
            "ORDER BY sum(o.quantity) DESC")
    List<Object[]> getProductsAmount();

    @Query(value = "SELECT p.name, o.quantity FROM ORDERS AS o\n" +
            "JOIN orders_history AS oh ON  o.id = oh.order_id\n" +
            "JOIN status s ON oh.status_id = s.id\n" +
            "JOIN product p ON o.product_id = p.id\n" +
            "WHERE o.customer_id = :customerId AND oh.status_id = :statusId", nativeQuery = true)
    List<Object[]> getOrdersByCustomerIdAndStatusId(@Param("customerId") Long customerId,
                                                    @Param("statusId")Long statusId);
}
