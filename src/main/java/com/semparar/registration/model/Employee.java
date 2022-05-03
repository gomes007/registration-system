package com.semparar.registration.model;

import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String cpf;
    private String phone;
    private LocalDateTime birthDate;

    private String gender;
    private boolean retired;
    private String maritalStatus;
    private String otherInformations;
    private Double salary;

    private String [] languages;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Address> address = new ArrayList<>();

}
