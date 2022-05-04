package com.semparar.registration.service;

import com.semparar.registration.model.Address;
import com.semparar.registration.model.Employee;
import com.semparar.registration.repository.AddressRepository;
import com.semparar.registration.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {


    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

/*
    @Transactional
    public ResponseEntity<Employee> saveEmployee(Employee employee){

        ArrayList<Address> addresses = new ArrayList<>();

        try {
            employee.getAddress().forEach((Address address)-> {
                addresses.add(addressRepository.save(address));
            });
            employee.setAddress(addresses);
            Employee savedEmployee = employeeRepository.save(employee);
            return ResponseEntity.ok(savedEmployee);
        }
        catch (Exception exception){
            return ResponseEntity.badRequest().build();
        }
    }
    */

    @Transactional
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    @Transactional
    public void saveAddress(Employee employee, Address[] addresses) {

        List<Address> items = Arrays.asList(addresses);

        items.forEach(address -> {
            address.setEmployee(employee);
            addressRepository.save(address);
        });

    }


    public ResponseEntity<List<Employee>> listEmployees() {
        try {
            ArrayList<Employee> employees = (ArrayList<Employee>) employeeRepository.findAll();
            return ResponseEntity.ok(employees);
        } catch (Exception exception) {
            return ResponseEntity.badRequest().build();
        }
    }


}
