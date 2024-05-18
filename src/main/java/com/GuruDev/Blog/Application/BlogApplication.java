package com.GuruDev.Blog.Application;

import com.GuruDev.Blog.Application.Config.Constant;
import com.GuruDev.Blog.Application.Exceptions.InvalidCredentialsException;
import com.GuruDev.Blog.Application.Model.Role;
import com.GuruDev.Blog.Application.Repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	@Autowired
	private RoleRepo roleRepo ;

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception{
		try{
			Role admin = new Role();
			admin.setId(Constant.ADMIN_USER);
			admin.setName("ROLE_ADMIN");

			Role user = new Role();
			user.setId(Constant.NORMAL_USER);
			user.setName("ROLE_NORMAL");


			List<Role> roles = List.of(admin,user);
			List<Role> result = roleRepo.saveAll(roles);

			result.forEach(r->
					System.out.println(r.getName()));


		}catch (Exception e)
		{
			throw new InvalidCredentialsException("SomeThing Went Wrong");
		}
	}


}