package claiborne.blog.api.springboot.service.impl;

import claiborne.blog.api.springboot.entity.Post;
import claiborne.blog.api.springboot.payload.PostDto;
import claiborne.blog.api.springboot.repository.PostRepository;
import claiborne.blog.api.springboot.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

  // constructor-based dependency injection
  private PostRepository postRepository;

  // we can omit @Autowired annotation because 1) we've registered as a bean (with @Service in this case),
  // and 2) only one constructor exists
  public PostServiceImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public PostDto create(PostDto postDto) {

    // convert DTO to entity
    Post post = new Post();
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent((postDto.getContent()));

    // returns Post entity
    Post newPost = postRepository.save(post);

    // convert entity to DTO
    PostDto postResponse = new PostDto();
    postResponse.setId(newPost.getId());
    postResponse.setTitle(newPost.getTitle());
    postResponse.setDescription(newPost.getDescription());
    postResponse.setContent(newPost.getContent());

    return postResponse;
  }
}
