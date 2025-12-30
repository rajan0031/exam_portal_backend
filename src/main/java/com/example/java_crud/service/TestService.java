package com.example.java_crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.java_crud.model.Test;
import com.example.java_crud.repository.TestRepository;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test CreateTest(Test test) {
        return testRepository.save(test);
    }

    // get all the tests creaed by a teacherby the id of the teacher

    public List<Test> GetAllTest(Long teacher_id) {
        return testRepository.findByTeacherId(teacher_id);
    }

}
