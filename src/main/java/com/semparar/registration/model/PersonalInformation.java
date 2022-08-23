package com.semparar.registration.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInformation {

    private String name;
    private String email;
    private String cpf;
    private String phone;
    private LocalDate birthDate;
    private String gender;
    private String otherInformations;
}
