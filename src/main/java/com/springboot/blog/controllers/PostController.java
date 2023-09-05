package com.springboot.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.blog.config.AppConstants;
import com.springboot.blog.mappers.PostMapper;
import com.springboot.blog.payloads.response.ApiResponse;
import com.springboot.blog.payloads.response.PostResponse;
import com.springboot.blog.payloads.request.PostRequest;
import com.springboot.blog.services.FileService;
import com.springboot.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	// create new post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest,
			@PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId) {
		PostResponse createPost = this.postService.createPost(postRequest, userId, categoryId);
		return new ResponseEntity<PostResponse>(createPost, HttpStatus.CREATED);
	}

	// get post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostResponse>> getPostByUser(@PathVariable("userId") Integer userId) {
		List<PostResponse> postByUser = this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostResponse>>(postByUser, HttpStatus.OK);
	}

	// get post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostResponse>> getPostByCategory(@PathVariable("categoryId") Integer categoryId) {
		List<PostResponse> postByCategory = this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostResponse>>(postByCategory, HttpStatus.OK);
	}

	// get post by post id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostResponse> getPostById(@PathVariable("postId") Integer postId) {
		PostResponse postById = this.postService.getPostById(postId);
		return new ResponseEntity<PostResponse>(postById, HttpStatus.OK);
	}

	// get all posts
	@GetMapping("/posts")
	public ResponseEntity<List<PostResponse>> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
		List<PostResponse> allPosts = this.postService.getAllPosts(pageNumber, pageSize);
		return new ResponseEntity<List<PostResponse>>(allPosts, HttpStatus.OK);
	}

	// get all posts by sorting
	@GetMapping("/posts/sort")
	public ResponseEntity<PostResponse> getAllPostsByPage(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		PostResponse postResponse = this.postService.getAllPostsBySorting(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
	}

	// get all posts by searching
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostResponse>> searchPostByTitle(@PathVariable("keywords") String keywords) {
		List<PostResponse> searchPost = this.postService.searchPost(keywords);
		return new ResponseEntity<List<PostResponse>>(searchPost, HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post is deleted successfully !!", true), HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostResponse> updatePost(@RequestBody PostRequest postRequest,
			@PathVariable("postId") Integer postId) {
		PostResponse updatedPost = this.postService.updatePost(postRequest, postId);
		return new ResponseEntity<PostResponse>(updatedPost, HttpStatus.OK);
	}

	// post image upload
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostResponse> uploadImage(@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId) throws IOException {

		PostResponse postById = this.postService.getPostById(postId);

		String uploadImage = this.fileService.uploadImage(path, image);
		postById.setPostImageName(uploadImage);

		PostRequest postRequest = PostMapper.INSTANCE.MapResponseToRequest(postById);

		PostResponse updatedPostResponse = this.postService.updatePost(postRequest, postId);

		return new ResponseEntity<PostResponse>(updatedPostResponse, HttpStatus.OK);

	}

	// method to serve files
	@GetMapping(value = "/posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {

		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());

	}

}
