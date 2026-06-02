package com.example.demo235.controller;

import com.example.demo235.model.Car;
import com.example.demo235.model.CarType;
import com.example.demo235.repository.CarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/car-types")
public class CarTypeController {
    @Autowired
    private CarTypeRepository carTypeRepository;

    @GetMapping
    public ModelAndView getList() {
        List<CarType> cars = carTypeRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("/carType/list");
        modelAndView.addObject("arr", cars);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView getForm() {
        return new ModelAndView("/carType/add");
    }

    @PostMapping("/save")
    public ModelAndView saveNew(@RequestParam String name) {
        CarType newCarType = new CarType(name);
        carTypeRepository.save(newCarType);
        return new ModelAndView("redirect:/car-types");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView getEditForm(@PathVariable Long id) {
        Optional<CarType> carType = carTypeRepository.findById(id);
        ModelAndView modelAndView = new ModelAndView("/carType/edit");
        modelAndView.addObject("car", carType.get());
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@RequestParam Long id, @RequestParam String name) {
        CarType car = carTypeRepository.findById(id).get();
        car.setName(name);
        carTypeRepository.save(car);
        return new ModelAndView("redirect:/car-types");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        carTypeRepository.deleteById(id);
        return new ModelAndView("redirect:/car-types");
    }

}
