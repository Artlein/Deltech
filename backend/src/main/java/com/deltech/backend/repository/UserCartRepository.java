package com.deltech.backend.repository;

import com.deltech.backend.entity.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Long> {
    List<UserCart> findByCustomerId(Long customerId);
    Optional<UserCart> findByCustomerIdAndProductId(Long customerId, Long productId);
    void deleteByCustomerId(Long customerId);
    void deleteByCustomerIdAndProductId(Long customerId, Long productId);
}
