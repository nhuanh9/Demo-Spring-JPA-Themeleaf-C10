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
    private List<Product> listProduct = new ArrayList<>();

    public HelloController() {
        listProduct.add(new Product(100, "IP"));
        listProduct.add(new Product(111, "IP1"));
        listProduct.add(new Product(222, "IP2"));
        listProduct.add(new Product(333, "IP3"));
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
        Product product = new Product(200, "IP");
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
        list.add(new Product(100, "IP"));
        list.add(new Product(111, "IP1"));
        list.add(new Product(222, "IP2"));
        list.add(new Product(333, "IP3"));
        modelAndView.addObject("arr", list);
        return modelAndView;
    }

    @GetMapping("/ex5")
    public ModelAndView ex5(@RequestParam int a, @RequestParam int b) {
        ModelAndView modelAndView = new ModelAndView("/ex5");
        modelAndView.addObject("sum", a+b);
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView form() {
        return new ModelAndView("/form");
    }

    @GetMapping("/cal")
    public ModelAndView cal(@RequestParam int a, @RequestParam int b) {
        ModelAndView modelAndView = new ModelAndView("/rsCal");
        modelAndView.addObject("tong", a+b);
        modelAndView.addObject("tich", a*b);
            modelAndView.addObject("hieu", a-b);
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("/formLogin");
    }

    @GetMapping("/products")
    public ModelAndView listProducts() {
        ModelAndView modelAndView = new ModelAndView("/product/list");
        modelAndView.addObject("products", listProduct);
        return modelAndView;
    }

    @GetMapping("/add-product")
    public ModelAndView addProductForm() {
        return new ModelAndView("/product/add");
    }

    @PostMapping("/add-product")
    public ModelAndView addProduct(Product product) {
        listProduct.add(product);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/edit-product")
    public ModelAndView editProductForm(@RequestParam int index) {
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        modelAndView.addObject("product", listProduct.get(index));
        modelAndView.addObject("index", index);
        return modelAndView;
    }

    @PostMapping("/edit-product")
    public ModelAndView editProduct(@RequestParam int index, Product product) {
        listProduct.set(index, product);
        return new ModelAndView("redirect:/products");
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
