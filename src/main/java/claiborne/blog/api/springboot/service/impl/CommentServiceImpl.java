package claiborne.blog.api.springboot.service.impl;

import claiborne.blog.api.springboot.entity.Comment;
import claiborne.blog.api.springboot.entity.Post;
import claiborne.blog.api.springboot.exception.BlogAPIException;
import claiborne.blog.api.springboot.exception.ResourceNotFoundException;
import claiborne.blog.api.springboot.payload.CommentDto;
import claiborne.blog.api.springboot.repository.CommentRepository;
import claiborne.blog.api.springboot.repository.PostRepository;
import claiborne.blog.api.springboot.service.CommentService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
  // constructor-based dependency injection
  private CommentRepository commentRepository;
  private PostRepository postRepository;

  // we can omit @Autowired annotation because 1) we've registered as a bean (with @Service in this case),
  // and 2) only one constructor exists
  public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
    this.commentRepository = commentRepository;
    this.postRepository = postRepository;
  }

  @Override
  public CommentDto create(long postId, CommentDto commentDto) {
    Comment entity = dtoToEntity(commentDto);
    Post post = postRepository.findById(postId).orElseThrow(
        () -> new ResourceNotFoundException("Post", "id", postId));
    entity.setPost(post);

    Comment comment = commentRepository.save(entity);

    return entityToDto(comment);
  }

  @Override
  public List<CommentDto> getByPostId(long postId) {
    List<Comment> comments = commentRepository.findByPostId(postId);
    return comments.stream().map(comment -> entityToDto(comment)).collect(Collectors.toList());
  }

  @Override
  public CommentDto getById(long postId, long commentId) {
    Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
    Comment comment = commentRepository.findById(commentId).orElseThrow(
        () -> new ResourceNotFoundException("Comment", "id", commentId));
    if(!comment.getPost().getId().equals(post.getId())) {
      throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to this post");
    }
    return entityToDto(comment);
  }

  private CommentDto entityToDto(Comment entity) {
    CommentDto dto = new CommentDto();
    dto.setId(entity.getId());
    dto.setName(entity.getName());
    dto.setBody(entity.getBody());

    return dto;
  }

  private Comment dtoToEntity(CommentDto dto) {
    Comment entity = new Comment();
    entity.setId(dto.getId());
    entity.setName(dto.getName());
    entity.setBody(dto.getBody());

    return entity;
  }

}
