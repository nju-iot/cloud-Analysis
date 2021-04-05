package com.example.cloudbiz1.controller;

import com.example.cloudbiz1.Entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author xmx
 * @date 2021/3/14
 **/

@RestController
@RequestMapping(value = "/student")
public class StudentController {


    @GetMapping(value = "/detail")
    public Student getStudent(){

        //模拟超时

        Student student = new Student();
        student.setName("Rocky");
        student.setAge(10);

        return student;
    }
}
