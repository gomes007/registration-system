package com.semparar.registration.controller;

import com.semparar.registration.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello world");
    }
}
