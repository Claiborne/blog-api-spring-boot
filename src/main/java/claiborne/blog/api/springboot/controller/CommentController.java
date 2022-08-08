package claiborne.blog.api.springboot.controller;

import claiborne.blog.api.springboot.payload.CommentDto;
import claiborne.blog.api.springboot.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

  private CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @PostMapping("/posts/{posdId}/comments")
  public ResponseEntity<CommentDto> create(@PathVariable(value = "posdId") long postId,
                                           @RequestBody CommentDto commentDto) {
    return new ResponseEntity<>(commentService.create(postId, commentDto), HttpStatus.CREATED);
  }

  @GetMapping("/posts/{postId}/comments")
  public List<CommentDto> getAllByPostId(@PathVariable(value = "postId") long postId) {
    return commentService.getByPostId(postId);
  }
}
