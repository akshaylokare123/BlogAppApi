package com.springboot.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.blog.config.AppConstants;
import com.springboot.blog.entities.Role;
import com.springboot.blog.repositories.RoleRepository;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}

    @Bean
    ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

//		System.out.println(this.passwordEncoder.encode("Tushar@123"));

		try {

			Role roleAdmin = new Role();
			roleAdmin.setRoleId(AppConstants.ADMIN_USER);
			roleAdmin.setRoleName("ROLE_ADMIN");

			Role roleNormal = new Role();
			roleNormal.setRoleId(AppConstants.NORMAL_USER);
			roleNormal.setRoleName("ROLE_NORMAL");

			List<Role> roles = List.of(roleAdmin, roleNormal);
			this.roleRepository.saveAll(roles);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
