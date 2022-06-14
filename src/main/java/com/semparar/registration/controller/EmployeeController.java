package com.semparar.registration.controller;

import com.semparar.registration.model.Address;
import com.semparar.registration.model.Employee;
import com.semparar.registration.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;



    @GetMapping
    public ResponseEntity<List<Employee>> listAll() {
        return employeeService.listEmployees();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Employee> findId(@PathVariable Long id) {
        Employee obj = employeeService.findId(id);
        return ResponseEntity.ok().body(obj);
    }


    @PostMapping
    public ResponseEntity save(@RequestBody Employee employee) {
        Employee objEmployee = employeeService.saveEmployee(employee);
        employeeService.saveAddress(objEmployee, objEmployee.getAddress().toArray(new Address[0]));
        return new ResponseEntity(objEmployee, HttpStatus.CREATED);
    }


    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return employeeService.findById(id).map(entity -> {
            employeeService.delete(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(() -> new ResponseEntity("Registry not found in database", HttpStatus.BAD_REQUEST));
    }







     /*
    @PostMapping
    public Employee save(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }
     */


}
