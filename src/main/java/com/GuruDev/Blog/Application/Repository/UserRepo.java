package com.GuruDev.Blog.Application.Repository;

import com.GuruDev.Blog.Application.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {


}
