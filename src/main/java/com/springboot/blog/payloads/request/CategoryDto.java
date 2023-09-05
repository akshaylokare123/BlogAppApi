package com.springboot.blog.payloads.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class CategoryDto {

	private Integer categoryId;

	@NotEmpty
	@Size(min = 4, max=100)
	private String categoryTitle;

	@NotEmpty
	private String categoryDescription;
}
