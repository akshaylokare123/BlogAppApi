package com.springboot.blog.payloads.request;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
	
	private String postTitle;

	private String postContent;

	private String postImageName;

//	private Date postAddedDate;
//
//	private CategoryDto category;
//
//	private UserDto user;
//
//	private Set<CommentDto> comments = new HashSet<>();
	
}
