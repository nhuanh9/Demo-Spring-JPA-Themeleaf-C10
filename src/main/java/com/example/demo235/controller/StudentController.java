package com.example.demo235.controller;

import com.example.demo235.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(1, "a", 8));
        students.add(new Student(2, "b", 7));
        students.add(new Student(3, "ca", 9));
        students.add(new Student(4, "db", 10));
        students.add(new Student(5, "eab", 8));
        students.add(new Student(6, "a", 10));
        students.add(new Student(7, "b", 8));
        students.add(new Student(8, "ca", 9));
        students.add(new Student(9, "db", 9));
        students.add(new Student(10, "eab", 5));
    }

    @GetMapping
    public ModelAndView getList() {
        ModelAndView modelAndView = new ModelAndView("/student/list");
        modelAndView.addObject("arr", students);
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView getList(@RequestParam String key) {
        ModelAndView modelAndView = new ModelAndView("/student/list");
        List<Student> rs = new ArrayList<>();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().contains(key)) {
                rs.add(students.get(i));
            }
        }
        modelAndView.addObject("arr", rs);
        return modelAndView;
    }

    @GetMapping("/sort2")
    public ModelAndView sort2() {
        ModelAndView modelAndView = new ModelAndView("/student/list");
        students.sort(Comparator.comparingInt(Student::getScore).reversed());
        modelAndView.addObject("arr", students);
        return modelAndView;
    }

    @GetMapping("/sort")
    public ModelAndView sort() {
        ModelAndView modelAndView = new ModelAndView("/student/list");
        students.sort(Comparator.comparingInt(student -> student.getScore()));
        modelAndView.addObject("arr", students);
        return modelAndView;
    }

    @GetMapping("/top3")
    public ModelAndView top3() {
        ModelAndView modelAndView = new ModelAndView("/student/list");
        List<Student> top3Student = students.stream().sorted(Comparator.comparingInt(Student::getScore).reversed()).limit(3).toList();
        modelAndView.addObject("arr", top3Student);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView getForm() {
        return new ModelAndView("/student/add");
    }

    @PostMapping("/add")
    public ModelAndView add(Student student) {
        students.add(student);
        return new ModelAndView("redirect:/students");
    }

    @GetMapping("/edit")
    public ModelAndView getEditForm(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("/student/edit");
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                modelAndView.addObject("editStudent", students.get(i));
            }
        }
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/students");
        int indexDelete = -1;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                indexDelete = i;
            }
        }
        students.remove(indexDelete);
        return modelAndView;
    }


    @PostMapping("/edit")
    public ModelAndView getEditForm(Student student) {
        ModelAndView modelAndView = new ModelAndView("redirect:/students");
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
            }
        }
        return modelAndView;
    }

}
