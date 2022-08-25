package claiborne.blog.api.springboot.controller;

import claiborne.blog.api.springboot.payload.PostDto;
import claiborne.blog.api.springboot.payload.PostResponse;
import claiborne.blog.api.springboot.service.PostService;
import claiborne.blog.api.springboot.utils.AppConfigs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "API for Blog Posts")
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

  // note: interface not implementation class for loose coupling
  private PostService postService;

  // ok to omit @Autowired b/c this class is registered as a bean and has only one constructor
  public PostController(PostService postService) {
    this.postService = postService;
  }

  @ApiOperation(value = "Create a blog post")
  // @PreAuthorize("hasRole('ROLE_ADMIN')")
  // @PostMapping impl has: @RequestMapping(method=RequestMethod.POST)
  @PostMapping
  public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
    return new ResponseEntity<>(postService.create(postDto), HttpStatus.CREATED);
  }

  @ApiOperation(value = "Get all blog posts")
  @GetMapping
  public PostResponse getAllPosts(
      @RequestParam(value = "page", defaultValue = AppConfigs.DEFAULT_PAGE_NUMBER, required = false) int page,
      @RequestParam(value = "count", defaultValue = AppConfigs.DEFAULT_PAGE_SIZE, required = false) int count,
      @RequestParam(value = "sort", defaultValue = AppConfigs.DEFAULT_SORT_BY, required = false) String sort,
      @RequestParam(value = "order", defaultValue = AppConfigs.DEFAULT_SORT_ORDER, required = false) String order
  ) {
    return postService.getAll(page, count, sort, order);
  }

  @ApiOperation(value = "Get a blog post by id")
  @GetMapping("/{id}")
  public ResponseEntity<PostDto> getById(@PathVariable(name = "id") long id) {
    return ResponseEntity.ok(postService.getById(id));
  }

  @ApiOperation(value = "Update a blog post")
  @PutMapping("/{id}")
  public ResponseEntity<PostDto> update(@Valid @RequestBody PostDto dto, @PathVariable(name = "id") long id) {
    return new ResponseEntity<>(postService.update(dto, id), HttpStatus.OK);
  }

  @ApiOperation(value = "Delete a blog post")
  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable(name = "id") long id) {
    postService.delete(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
