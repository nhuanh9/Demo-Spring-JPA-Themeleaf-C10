package com.example.demo235.controller;

import com.example.demo235.model.CarType;
import com.example.demo235.repository.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/car-types")
@CrossOrigin("*")
public class CarTypeRestController {
    @Autowired
    private CarTypeRepository carTypeRepository;

    @GetMapping
    public ResponseEntity<List<CarType>> getList() {
        List<CarType> cars = carTypeRepository.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity saveNew(@RequestBody CarType carType) {
        carTypeRepository.save(carType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        Optional<CarType> carType = carTypeRepository.findById(id);
        return new ResponseEntity<>(carType, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody CarType carType) {
        carTypeRepository.save(carType);
        return new ResponseEntity<>(carType, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        carTypeRepository.deleteById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
