package com.GuruDev.Blog.Application.Payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Integer id;

    @Size(min = 4, message = "Username must be min 4 characters")
    private String name;

    @Email(message = "Email Address is not valid")
    private String email;

    @Size(min = 8, max = 15, message = "Password must be min 8 characters")
    private String password;
    private String about;
}
