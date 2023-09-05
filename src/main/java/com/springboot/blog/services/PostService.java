package com.springboot.blog.services;

import java.util.List;

import com.springboot.blog.payloads.request.PostRequest;
import com.springboot.blog.payloads.response.PostResponse;

public interface PostService {

	public PostResponse createPost(PostRequest postRequest, Integer userId, Integer categoryId);

	public PostResponse updatePost(PostRequest postRequest, Integer postId);

	public void deletePost(Integer postId);

	public List<PostResponse> getAllPosts(Integer pageNo, Integer pageSize);

	public PostResponse getAllPostsBySorting(Integer pageNo, Integer pageSize, String sortBy, String sortDir);

	public PostResponse getPostById(Integer postId);

	public List<PostResponse> getPostByCategory(Integer categoryId);

	public List<PostResponse> getPostByUser(Integer userId);

	public List<PostResponse> searchPost(String keyword);

}
