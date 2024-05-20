package com.GuruDev.Blog.Application.Model;

import com.GuruDev.Blog.Application.Payloads.UserDTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JWTResponse {

    private  String token ;
    private String userName ;
    private int userId ;
    private String userRole;

}
