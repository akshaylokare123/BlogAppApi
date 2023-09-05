package com.springboot.blog.payloads.request;

import lombok.Data;

@Data
public class JwtAuthRequest {
	
	private String username;
	private String password;
	
}
