package com.GuruDev.Blog.Application.Services;

import com.GuruDev.Blog.Application.Model.User;
import com.GuruDev.Blog.Application.Payloads.UserDTO;

import java.util.List;

public interface UserService {

   UserDTO createUser(UserDTO userDTO);
   UserDTO updateUser(UserDTO userDTO , Integer userId);
   UserDTO getUserById(Integer userId);
   List<UserDTO> getAllUsers();
   void deleteUser(Integer userId);

}
