package com.springboot.blog.payloads.request;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 4, message = "Username must be minimum 4 characters")
	private String name;

	@Email(message = "Email address is not valid !!")
	private String email;

	@NotEmpty
	@Size(min = 4, max = 10, message = "Password length must be minimum 4 and maximum 8 characters")
//	@JsonIgnore
	private String password;

	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles = new HashSet<>();

}
