package com.example.demo235.controller;

import com.example.demo235.model.Car;
import com.example.demo235.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cars")
public class CarController {
    @Autowired
    private CarRepository carRepository;

    @GetMapping
    public ModelAndView getList() {
        List<Car> cars = carRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("/car/list");
        modelAndView.addObject("arr", cars);
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String name) {
        List<Car> cars = carRepository.findAllByNameContaining(name);
        ModelAndView modelAndView = new ModelAndView("/car/list");
        modelAndView.addObject("arr", cars);
        return modelAndView;
    }

    @GetMapping("/sort")
    public ModelAndView sort(@RequestParam String type) {
        ModelAndView modelAndView = new ModelAndView("/car/list");
        List<Car> cars;
        if (type.equals("asc")) {
            cars = carRepository.findAllByOrderByPriceAsc();
        } else {
            cars = carRepository.findAllByOrderByPriceDesc();
        }
        modelAndView.addObject("arr", cars);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView getForm() {
        return new ModelAndView("/car/add");
    }

    @PostMapping("/save")
    public ModelAndView saveNew(@RequestParam String name, @RequestParam Double price) {
        Car newCar = new Car(name, price);
        carRepository.save(newCar);
        return new ModelAndView("redirect:/cars");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getEditForm(@PathVariable Long id) {
        Optional<Car> car = carRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView("/car/edit");
        modelAndView.addObject("car", car.get());
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@RequestParam Long id, @RequestParam String name, @RequestParam Double price) {
        Car car = carRepository.findById(id).get();
        car.setName(name);
        car.setPrice(price);
        carRepository.save(car);
        return new ModelAndView("redirect:/cars");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        carRepository.deleteById(id);
        return new ModelAndView("redirect:/cars");
    }

}
