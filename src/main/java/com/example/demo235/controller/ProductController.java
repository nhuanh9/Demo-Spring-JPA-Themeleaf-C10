package com.example.demo235.controller;

import com.example.demo235.model.Category;
import com.example.demo235.model.Product;
import com.example.demo235.repository.CategoryRepository;
import com.example.demo235.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ModelAndView list(@RequestParam(required = false) Long categoryId) {
        ModelAndView modelAndView = new ModelAndView("/product/list");
        List<Product> products;
        if (categoryId != null) {
            products = productRepository.findAllByCategoryId(categoryId);
        } else {
            products = productRepository.findAll();
        }
        List<Category> categories = categoryRepository.findAll();
        modelAndView.addObject("products", products);
        modelAndView.addObject("categories", categories);
        modelAndView.addObject("selectedCategoryId", categoryId);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addForm() {
        ModelAndView modelAndView = new ModelAndView("/product/add");
        List<Category> categories = categoryRepository.findAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@RequestParam String name, @RequestParam Double price, @RequestParam Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        Product product = new Product(name, price, category);
        productRepository.save(product);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        Product product = productRepository.findById(id).orElseThrow();
        List<Category> categories = categoryRepository.findAll();
        modelAndView.addObject("product", product);
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@RequestParam Long id, @RequestParam String name, @RequestParam Double price, @RequestParam Long categoryId) {
        Product product = productRepository.findById(id).orElseThrow();
        Category category = categoryRepository.findById(categoryId).orElse(null);
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        productRepository.save(product);
        return new ModelAndView("redirect:/products");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return new ModelAndView("redirect:/products");
    }
}
