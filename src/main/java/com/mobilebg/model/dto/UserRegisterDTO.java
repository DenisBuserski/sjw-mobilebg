package com.mobilebg.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
}
