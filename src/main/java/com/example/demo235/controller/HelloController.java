package com.example.demo235.controller;

import com.example.demo235.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    public HelloController() {
    }

    @GetMapping("/hello")
    public ModelAndView hello() {
        return new ModelAndView("/hello");
    }

    @GetMapping("/ex1")
    public ModelAndView ex1() {
        ModelAndView modelAndView = new ModelAndView("/ex1");
        modelAndView.addObject("myName", "NA");
        return modelAndView;
    }

    @GetMapping("/ex2")
    public ModelAndView ex2() {
        ModelAndView modelAndView = new ModelAndView("/ex2");
        Product product = new Product("IP", 200.0, null);
        modelAndView.addObject("p", product);
        return modelAndView;
    }

    @GetMapping("/ex3")
    public ModelAndView ex3() {
        ModelAndView modelAndView = new ModelAndView("/ex3");
        List<String> list = new ArrayList<>();
        list.add("NAL");
        list.add("Huong");
        list.add("Duy");
        list.add("Bac");
        modelAndView.addObject("arr", list);
        return modelAndView;
    }

    @GetMapping("/ex4")
    public ModelAndView ex4() {
        ModelAndView modelAndView = new ModelAndView("/ex4");
        List<Product> list = new ArrayList<>();
        list.add(new Product("IP", 100.0, null));
        list.add(new Product("IP1", 111.0, null));
        list.add(new Product("IP2", 222.0, null));
        list.add(new Product("IP3", 333.0, null));
        modelAndView.addObject("arr", list);
        return modelAndView;
    }

    @GetMapping("/ex5")
    public ModelAndView ex5(@RequestParam int a, @RequestParam int b) {
        ModelAndView modelAndView = new ModelAndView("/ex5");
        modelAndView.addObject("sum", a + b);
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView form() {
        return new ModelAndView("/form");
    }

    @GetMapping("/cal")
    public ModelAndView cal(@RequestParam int a, @RequestParam int b) {
        ModelAndView modelAndView = new ModelAndView("/rsCal");
        modelAndView.addObject("tong", a + b);
        modelAndView.addObject("tich", a * b);
        modelAndView.addObject("hieu", a - b);
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("/formLogin");
    }

    @PostMapping("/login")
    public ModelAndView loginSubmit(@RequestParam String usn, @RequestParam String pass) {
        if (usn.equals("admin") && pass.equals("123")) {
            return new ModelAndView("/admin");
        }
        if (usn.equals("user") && pass.equals("123")) {
            return new ModelAndView("/user");
        }
        return new ModelAndView("/notFound");
    }
}
