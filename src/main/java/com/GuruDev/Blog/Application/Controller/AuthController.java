package com.GuruDev.Blog.Application.Controller;

import com.GuruDev.Blog.Application.Exceptions.InvalidCredentialsException;
import com.GuruDev.Blog.Application.Exceptions.ResourceNotFoundException;
import com.GuruDev.Blog.Application.Model.JWTRequest;
import com.GuruDev.Blog.Application.Model.JWTResponse;
import com.GuruDev.Blog.Application.Model.User;
import com.GuruDev.Blog.Application.Payloads.ApiResponse;
import com.GuruDev.Blog.Application.Payloads.ChangePassDTO;
import com.GuruDev.Blog.Application.Payloads.UserDTO;
import com.GuruDev.Blog.Application.Repository.UserRepo;
import com.GuruDev.Blog.Application.Security.JwtHelper;
import com.GuruDev.Blog.Application.Services.UserService;
import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        User user = this.userRepo.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User", "Email" + userDetails.getUsername(), 0));

        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);

        JWTResponse response = JWTResponse.builder()
                .token(token)
                .userId(userDTO.getId())
                .userRole(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .findFirst()
                        .orElse(""))
                .userName(userDetails.getUsername()).build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
