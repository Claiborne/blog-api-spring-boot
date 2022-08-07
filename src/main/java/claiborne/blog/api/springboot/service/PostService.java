package claiborne.blog.api.springboot.service;

import claiborne.blog.api.springboot.payload.PostDto;

import java.util.List;

public interface PostService {

  PostDto create(PostDto postDto);

  List<PostDto> getAll();

  PostDto getById(long id);

  PostDto update(PostDto post, long id);

  void delete(long id);
}
