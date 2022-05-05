package com.semparar.registration.controller;

import com.semparar.registration.model.Address;
import com.semparar.registration.model.Employee;
import com.semparar.registration.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    public ResponseEntity<List<Employee>> listAll() {
        return employeeService.listEmployees();
    }


    /*
    @PostMapping
    public Employee save(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }
     */


    @PostMapping
    public ResponseEntity save(@RequestBody Employee employee) {
        Employee objEmployee = employeeService.saveEmployee(employee);
        employeeService.saveAddress(objEmployee, objEmployee.getAddress().toArray(new Address[0]));
        return new ResponseEntity(objEmployee, HttpStatus.CREATED);
    }




}
