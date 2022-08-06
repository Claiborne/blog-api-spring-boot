package claiborne.blog.api.springboot.service.impl;

import claiborne.blog.api.springboot.entity.Post;
import claiborne.blog.api.springboot.payload.PostDto;
import claiborne.blog.api.springboot.repository.PostRepository;
import claiborne.blog.api.springboot.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    // convert DTO to Entity
    Post post = dtoToEntity(postDto);

    // returns Post entity
    Post newPost = postRepository.save(post);

    // convert Entity to DTO
    return entityToDTO(newPost);
  }

  @Override
  public List<PostDto> getAll() {
    List<Post> posts = postRepository.findAll();
    return posts.stream().map(post -> entityToDTO(post)).collect(Collectors.toList());
  }

  // convert Entity to DTO
  private PostDto entityToDTO(Post entity) {
    PostDto dto = new PostDto();
    dto.setId(entity.getId());
    dto.setTitle(entity.getTitle());
    dto.setDescription(entity.getDescription());
    dto.setContent(entity.getContent());

    return dto;
  }

  // convert DTO to Entity
  private Post dtoToEntity(PostDto dto) {
    Post entity = new Post();
    entity.setTitle(dto.getTitle());
    entity.setDescription(dto.getDescription());
    entity.setContent((dto.getContent()));

    return entity;
  }
}
