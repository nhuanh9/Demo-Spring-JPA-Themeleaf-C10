package com.example.demo235.repository;

import com.example.demo235.model.Car;
import com.example.demo235.model.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarTypeRepository extends JpaRepository<CarType, Long> {
}
