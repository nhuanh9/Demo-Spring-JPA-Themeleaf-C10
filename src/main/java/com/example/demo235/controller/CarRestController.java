package com.example.demo235.controller;

import com.example.demo235.model.Car;
import com.example.demo235.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin("*")
public class CarRestController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public ResponseEntity<List<Car>> getList(@RequestParam(required = false) String name) {
        List<Car> cars;
        if ( name != null) {
            cars = carRepository.findAllByNameContaining(name);
        } else {
            cars = carRepository.findAll();
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNew(@RequestBody Car car) {
        carRepository.save(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        Optional<Car> car = carRepository.findById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody Car car) {
        carRepository.save(car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        carRepository.deleteById(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

}
