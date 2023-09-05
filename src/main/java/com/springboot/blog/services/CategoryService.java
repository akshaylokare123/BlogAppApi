package com.springboot.blog.services;

import java.util.List;

import com.springboot.blog.payloads.request.CategoryDto;

public interface CategoryService {

	// create
	public CategoryDto createCategory(CategoryDto categoryDto);

	// get all
	public List<CategoryDto> getAllCategory();

	// get by id
	public CategoryDto getCategoryById(Integer categoryId);

	// update
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// delete
	public void deleteCategory(Integer categoryId);

}
