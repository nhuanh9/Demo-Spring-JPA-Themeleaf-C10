package com.example.demo235.controller;

import com.example.demo235.model.Category;
import com.example.demo235.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("/category/list");
        List<Category> categories = categoryRepository.findAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addForm() {
        return new ModelAndView("/category/add");
    }

    @PostMapping("/save")
    public ModelAndView save(@RequestParam String name) {
        Category category = new Category(name);
        categoryRepository.save(category);
        return new ModelAndView("redirect:/categories");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/category/edit");
        Category category = categoryRepository.findById(id).orElseThrow();
        modelAndView.addObject("category", category);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(@RequestParam Long id, @RequestParam String name) {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setName(name);
        categoryRepository.save(category);
        return new ModelAndView("redirect:/categories");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return new ModelAndView("redirect:/categories");
    }
}
