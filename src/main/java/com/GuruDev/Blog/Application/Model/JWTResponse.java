package com.GuruDev.Blog.Application.Model;

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

}
