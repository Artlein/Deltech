package com.deltech.backend.repository;

import com.deltech.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryOrderByCreatedAtDesc(String category);
    List<Product> findAllByOrderByCategoryAscCreatedAtDesc();
    List<Product> findByNameProductContainingIgnoreCase(String keyword);
}
