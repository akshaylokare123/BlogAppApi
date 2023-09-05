package com.springboot.blog.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.springboot.blog.entities.Post;
import com.springboot.blog.payloads.request.PostRequest;
import com.springboot.blog.payloads.response.PostResponse;

@Mapper(componentModel = "spring")
public interface PostMapper {

	PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

	Post MapRequestToEntity(PostRequest request);

	PostResponse MapEntityToResponse(Post post);

}
