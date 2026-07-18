package com.deltech.backend.repository;

import com.deltech.backend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerIdOrderByOrderDateDesc(Long customerId);
    Optional<Order> findByOrdersNumber(String ordersNumber);

    @Query("SELECT SUM(CAST(o.subtotal AS double)) FROM Order o WHERE o.orderStatus = 'completed'")
    Double sumCompletedSubtotals();

    @Query("SELECT COUNT(o) FROM Order o WHERE o.orderStatus = 'completed'")
    Long countCompleted();
}
