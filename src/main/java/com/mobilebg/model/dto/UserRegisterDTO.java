package com.mobilebg.model.dto;


import com.mobilebg.model.validation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO {
    @NotEmpty(message = "User email should be provided!")
    @Email(message = "User email should be valid!")
    @UniqueUserEmail(message = "User email should be unique!")
    private String email;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;

    @NotEmpty
    @Size(min = 5)
    private String password;

    private String confirmPassword;
}
