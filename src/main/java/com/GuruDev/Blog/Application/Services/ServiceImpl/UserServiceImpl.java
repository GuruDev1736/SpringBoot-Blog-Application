package com.GuruDev.Blog.Application.Services.ServiceImpl;

import com.GuruDev.Blog.Application.Exceptions.ResourceNotFoundException;
import com.GuruDev.Blog.Application.Model.User;
import com.GuruDev.Blog.Application.Payloads.UserDTO;
import com.GuruDev.Blog.Application.Repository.UserRepo;
import com.GuruDev.Blog.Application.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = this.DTOtoUser(userDTO);
        User save = this.userRepo.save(user);
        return this.UsertoUserDTO(save);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());

        User updatedUser = this.userRepo.save(user);

        return this.UsertoUserDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

        return this.UsertoUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> users = this.userRepo.findAll();
        List<UserDTO> userDTOS = users.stream().map(user -> this.UsertoUserDTO(user)).collect(Collectors.toList());

        return userDTOS ;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));

        this.userRepo.delete(user);

    }

    private User DTOtoUser(UserDTO userDTO)
    {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAbout(userDTO.getAbout());

        return user ;
    }

    private UserDTO UsertoUserDTO(User user)
    {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAbout(user.getAbout());

        return userDTO ;
    }
}
