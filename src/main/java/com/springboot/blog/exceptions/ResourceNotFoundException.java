package com.springboot.blog.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	String resourceName;
	String resourceFieldName;
	long resourceValue;

	public ResourceNotFoundException(String resourceName, String resourceFieldName, long resourceValue) {
		
		super(String.format("%s not found with %s : %s", resourceName, resourceFieldName, resourceValue));
		this.resourceName = resourceName;
		this.resourceFieldName = resourceFieldName;
		this.resourceValue = resourceValue;
	
	}

}
