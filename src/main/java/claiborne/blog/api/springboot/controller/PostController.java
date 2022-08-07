package claiborne.blog.api.springboot.controller;

import claiborne.blog.api.springboot.payload.PostDto;
import claiborne.blog.api.springboot.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  // note: interface not implementation class for loose coupling
  private PostService postService;

  // ok to omit @Autowired b/c this class is registered as a bean and has only one constructor
  public PostController(PostService postService) {
    this.postService = postService;
  }

  // @PostMapping impl has: @RequestMapping(method=RequestMethod.POST)
  @PostMapping
  public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
    return new ResponseEntity<>(postService.create(postDto), HttpStatus.CREATED);
  }

  @GetMapping
  public List<PostDto> getAllPosts() {
    return postService.getAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getById(@PathVariable(name = "id") long id) {
    return ResponseEntity.ok(postService.getById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PostDto> update(@RequestBody PostDto dto, @PathVariable(name = "id") long id) {
    return new ResponseEntity<>(postService.update(dto, id), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable(name = "id") long id) {
    postService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
