package claiborne.blog.api.springboot.service;

import claiborne.blog.api.springboot.payload.PostDto;

public interface PostService {

  PostDto create(PostDto postDto);

  List<PostDto> getAll();
}
