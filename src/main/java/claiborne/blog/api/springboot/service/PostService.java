package claiborne.blog.api.springboot.service;

import claiborne.blog.api.springboot.payload.PostDto;
import claiborne.blog.api.springboot.payload.PostResponse;

import java.util.List;

public interface PostService {

  PostDto create(PostDto postDto);

  PostResponse getAll(int page, int count, String sort, String order);

  PostDto getById(long id);

  PostDto update(PostDto post, long id);

  void delete(long id);
}
