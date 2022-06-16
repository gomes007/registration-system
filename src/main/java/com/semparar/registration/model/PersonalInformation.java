package com.semparar.registration.model;

import lombok.*;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInformation {

    private String name;
    private String email;
    private String cpf;
    private String phone;
    private LocalDateTime birthDate;
    private String gender;
    private String otherInformations;
}
