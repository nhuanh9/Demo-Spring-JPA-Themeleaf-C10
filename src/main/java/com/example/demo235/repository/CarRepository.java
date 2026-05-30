package com.example.demo235.repository;

import com.example.demo235.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByNameContaining(String name);
    List<Car> findAllByOrderByPriceAsc();
    List<Car> findAllByOrderByPriceDesc();
}
