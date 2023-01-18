package com.example.zeraki.web.dto;


import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

public class StudentRegistrationDto {


    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
