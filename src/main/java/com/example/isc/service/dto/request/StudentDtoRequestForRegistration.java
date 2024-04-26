package com.example.isc.service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDtoRequestForRegistration {
    @NotBlank(message = "firstname cannot be blank")
    private String firstname;

    @NotBlank(message = "lastname cannot be blank")
    private String lastname;


    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
    private String password;

    @Email
    private String email;

    @Pattern(regexp = "\\d{8}")
    private String nationalId;

    private LocalDate creationDate = LocalDate.now();

}
