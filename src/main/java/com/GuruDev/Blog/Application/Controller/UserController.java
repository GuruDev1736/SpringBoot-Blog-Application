package com.GuruDev.Blog.Application.Controller;

import com.GuruDev.Blog.Application.Payloads.ApiResponse;
import com.GuruDev.Blog.Application.Payloads.UserDTO;
import com.GuruDev.Blog.Application.Services.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer userId) {
        UserDTO updatedUser = this.userService.updateUser(userDTO, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
        this.userService.deleteUser(userId);
        return new ResponseEntity(new ApiResponse("User Has Successfully Deleted", true), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId) {
        UserDTO userDTO = this.userService.getUserById(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
