package com.example.demo235.repository;

import com.example.demo235.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryId(Long categoryId);
    List<Product> findAllByCategoryId(Long categoryId, Sort sort);
    List<Product> findByNameContainingIgnoreCase(String name, Sort sort);
    List<Product> findAllByCategoryIdAndNameContainingIgnoreCase(Long categoryId, String name, Sort sort);
}
