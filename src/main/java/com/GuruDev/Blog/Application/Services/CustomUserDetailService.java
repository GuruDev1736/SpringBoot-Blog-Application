package com.GuruDev.Blog.Application.Services;

import com.GuruDev.Blog.Application.Exceptions.ResourceNotFoundException;
import com.GuruDev.Blog.Application.Model.User;
import com.GuruDev.Blog.Application.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","Email",0));

        Set<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map((role -> new SimpleGrantedAuthority(role.getName())))
                .collect(Collectors.toSet());


        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
