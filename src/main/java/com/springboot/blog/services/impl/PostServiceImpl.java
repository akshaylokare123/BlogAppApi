package com.springboot.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.entities.Category;
import com.springboot.blog.entities.Post;
import com.springboot.blog.entities.User;
import com.springboot.blog.exceptions.ResourceNotFoundException;
import com.springboot.blog.mappers.PostMapper;
import com.springboot.blog.payloads.response.PostResponse;
import com.springboot.blog.payloads.request.PostRequest;
import com.springboot.blog.repositories.CategoryRepository;
import com.springboot.blog.repositories.PostRepository;
import com.springboot.blog.repositories.UserRepository;
import com.springboot.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public PostResponse createPost(PostRequest postRequest, Integer userId, Integer categoryId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

//		Post post = this.modelMapper.map(postRequest, Post.class);

		Post post = PostMapper.INSTANCE.MapRequestToEntity(postRequest);

		post.setPostImageName("default.png");
		post.setPostAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post savePost = this.postRepository.save(post);

//		return this.modelMapper.map(savePost, PostDto.class);

		PostResponse postResponse = PostMapper.INSTANCE.MapEntityToResponse(savePost);

		return postResponse;

	}

	@Override
	public PostResponse updatePost(PostRequest postRequest, Integer postId) {

		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

		post.setPostTitle(postRequest.getPostTitle());
		post.setPostContent(postRequest.getPostContent());
		post.setPostImageName(postRequest.getPostImageName());

		Post updatedPost = this.postRepository.save(post);

//		return this.modelMapper.map(updatedPost, PostDto.class);

		PostResponse postResponse = PostMapper.INSTANCE.MapEntityToResponse(updatedPost);

		return postResponse;

	}

	@Override
	public void deletePost(Integer postId) {

		Post post = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		this.postRepository.delete(post);

	}

	@Override
	public List<PostResponse> getAllPosts(Integer pageNo, Integer pageSize) {
		List<Post> allPosts = this.postRepository.findAll();

		List<PostResponse> allPostDto = allPosts.stream().map(list -> PostMapper.INSTANCE.MapEntityToResponse(list))
				.collect(Collectors.toList());

		return allPostDto;

	}

	@Override
	public PostResponse getAllPostsBySorting(Integer pageNo, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNo, pageSize, sort);
		Page<Post> pagePost = this.postRepository.findAll(p);
		
		List<Post> allPosts = pagePost.getContent();

//		List<PostDto> allPostDto = allPosts.stream().map(list -> this.modelMapper.map(list, PostDto.class))
//				.collect(Collectors.toList());

		List<PostResponse> allPostDto = allPosts.stream().map(list -> PostMapper.INSTANCE.MapEntityToResponse(list))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		
//		postResponse.setPostContent(allPostDto);
		
//		postResponse.setPageNumber(pagePost.getNumber());
//		postResponse.setPageSize(pagePost.getSize());
//		postResponse.setTotalElements(pagePost.getTotalElements());
//		postResponse.setTotalPages(pagePost.getTotalPages());
//		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostResponse getPostById(Integer postId) {

		Post postById = this.postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
//		return this.modelMapper.map(postById, PostDto.class);

		PostResponse postRespone = PostMapper.INSTANCE.MapEntityToResponse(postById);

		return postRespone;

	}

	@Override
	public List<PostResponse> getPostByCategory(Integer categoryId) {

		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

		List<Post> postByCategory = this.postRepository.findByCategory(category);

//		List<PostDto> listPostDto = postByCategory.stream().map(list -> this.modelMapper.map(list, PostDto.class))
//				.collect(Collectors.toList());

		List<PostResponse> listPostResponse = postByCategory.stream()
				.map(list -> PostMapper.INSTANCE.MapEntityToResponse(list)).collect(Collectors.toList());

		return listPostResponse;

	}

	@Override
	public List<PostResponse> getPostByUser(Integer userId) {

		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		List<Post> postByUser = this.postRepository.findByUser(user);

//		List<PostDto> listPostDto = postByUser.stream().map(list -> this.modelMapper.map(list, PostDto.class))
//				.collect(Collectors.toList());

		List<PostResponse> listPostResponse = postByUser.stream()
				.map(list -> PostMapper.INSTANCE.MapEntityToResponse(list)).collect(Collectors.toList());

		return listPostResponse;

	}

	@Override
	public List<PostResponse> searchPost(String keyword) {

		List<Post> postByTitle = this.postRepository.findByPostTitleContaining(keyword);

//		List<PostDto> listPostDto = postByTitle.stream().map(search -> this.modelMapper.map(search, PostDto.class))
//				.collect(Collectors.toList());

		List<PostResponse> listPostResponse = postByTitle.stream()
				.map(list -> PostMapper.INSTANCE.MapEntityToResponse(list)).collect(Collectors.toList());

		return listPostResponse;

	}

}
