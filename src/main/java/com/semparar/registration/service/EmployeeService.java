package com.semparar.registration.service;

import com.semparar.registration.model.Address;
import com.semparar.registration.model.Dependent;
import com.semparar.registration.model.Employee;
import com.semparar.registration.repository.AddressRepository;
import com.semparar.registration.repository.DependentRepository;
import com.semparar.registration.repository.EmployeeRepository;
import com.semparar.registration.service.exceptions.ObjctNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EmployeeService {


    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DependentRepository dependentRepository;

    private Employee employee = new Employee();

    /*
    @Transactional
    public ResponseEntity<Employee> saveEmployee1(Employee employee){

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


    @Transactional
    public void saveDependent(Employee employee, Dependent[] dependents) {
        List<Dependent> items = Arrays.asList(dependents);
        items.forEach(dependent -> {
            dependent.setEmployeeKinship(employee);
            dependentRepository.save(dependent);
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


    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }


    public Employee findId(Long id) {
        Optional<Employee> obj = employeeRepository.findById(id);
        return obj.orElseThrow(() -> new ObjctNotFoundException(
                "Registry not found! Id: " + id + ", Type: " + Employee.class.getName()));

    }


    @Transactional
    public void delete(Employee employee) {
        Objects.requireNonNull(employee.getId());
        employeeRepository.delete(employee);
    }

}


