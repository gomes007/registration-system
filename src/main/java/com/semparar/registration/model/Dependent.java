package com.semparar.registration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dependent")
public class Dependent {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String kinship;

    @Embedded
    private PersonalInformation personalInformation;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employeeKinship;

}
