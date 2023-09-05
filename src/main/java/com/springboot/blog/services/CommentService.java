package com.springboot.blog.services;

import com.springboot.blog.payloads.request.CommentDto;

public interface CommentService {

	public CommentDto createComment(CommentDto Dto, Integer postId);

	void deleteComment(Integer commentId);

}
