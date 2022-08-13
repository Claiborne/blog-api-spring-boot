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

  @GetMapping("/posts/{postId}/comments/{id}")
  public ResponseEntity<CommentDto> getById(@PathVariable(value = "postId") long postId,
                                            @PathVariable(value = "id") long id) {
    return new ResponseEntity<>(commentService.getById(postId, id), HttpStatus.OK);
  }

  @PutMapping("/posts/{postId}/comments/{id}")
  public ResponseEntity<CommentDto> update(@PathVariable(value = "postId") long postId,
                                           @PathVariable(value ="id") long id,
                                           @RequestBody CommentDto comment) {
    return new ResponseEntity<>(commentService.update(postId, id, comment), HttpStatus.OK);
  }

  @DeleteMapping("/posts/{postId}/comments/{id}")
  public ResponseEntity<String> delete(@PathVariable("postId") long postId,
                                       @PathVariable("id") long id) {
    commentService.delete(postId, id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
