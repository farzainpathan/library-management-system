package com.library.system.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/student")
public class StudentController {

    @GetMapping("/students")
    public String getAllStudents() {
        return "testing";
    }
}
