package claiborne.blog.api.springboot.service;

import claiborne.blog.api.springboot.payload.CommentDto;

import java.util.List;

public interface CommentService {

  CommentDto create(long postId, CommentDto commentDto);

  List<CommentDto> getByPostId(long postId);

  CommentDto getById(long postId, long CommentId);
}
