package com.example.java_crud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.java_crud.model.Test;
import com.example.java_crud.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/create")
    public Test CreateTest(@RequestBody Test test) {

        var val = testService.CreateTest(test);

        return val;
    }

    // get all th etst in the database by its teacher id

    @GetMapping("/alltest")
    public List<Test> GetAllTest(@RequestParam long teacher_id) {
        return testService.GetAllTest(teacher_id);
    }

}
