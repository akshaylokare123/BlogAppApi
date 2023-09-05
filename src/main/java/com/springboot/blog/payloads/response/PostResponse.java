package com.springboot.blog.payloads.response;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.springboot.blog.payloads.request.CategoryDto;
import com.springboot.blog.payloads.request.CommentDto;
import com.springboot.blog.payloads.request.PostRequest;
import com.springboot.blog.payloads.request.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
		
	private Integer postId;

	private String postTitle;

	private String postContent;

	private String postImageName;

	private Date postAddedDate;

	private CategoryDto category;

	private UserDto user;

	private Set<CommentDto> comments = new HashSet<>();
	
//	private int pageNumber;
//	private int pageSize;
//	private long totalElements;
//	private int totalPages;
//	private boolean isLastPage;

}
