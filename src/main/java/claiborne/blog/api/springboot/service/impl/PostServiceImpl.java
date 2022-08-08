package claiborne.blog.api.springboot.service.impl;

import claiborne.blog.api.springboot.entity.Post;
import claiborne.blog.api.springboot.exception.ResourceNotFoundException;
import claiborne.blog.api.springboot.payload.PostDto;
import claiborne.blog.api.springboot.payload.PostResponse;
import claiborne.blog.api.springboot.repository.PostRepository;
import claiborne.blog.api.springboot.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.border.Border;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
  public PostResponse getAll(int page, int count, String sort, String order) {
    Sort sortOrder = order.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sort).descending()
        : Sort.by(sort).ascending();

    Pageable pageable = PageRequest.of(page, count, sortOrder);

    // interface PagingAndSortingRepository<T, ID>
    // Page<T> findAll(Pageable pageable)
    Page<Post> posts = postRepository.findAll(pageable);

    List<Post> listPosts = posts.getContent();
    List<PostDto> content = listPosts.stream().map(post -> entityToDTO(post)).collect(Collectors.toList());

    PostResponse postResponse = new PostResponse();
    postResponse.setContent(content);
    postResponse.setPage(posts.getNumber());
    postResponse.setCount(posts.getSize());
    postResponse.setTotalElements(posts.getTotalElements());
    postResponse.setTotalPages(posts.getTotalPages());
    postResponse.setLast(posts.isLast());

    return postResponse;
  }

  @Override
  public PostDto getById(long id) {
    Post entity = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    return entityToDTO(entity);
  }

  @Override
  public PostDto update(PostDto dto, long id) {
    Post entity = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

    entity.setTitle(dto.getTitle());
    entity.setDescription(dto.getDescription());
    entity.setContent(dto.getContent());
    System.out.println(entity);

    Post post = postRepository.save(entity);
    return entityToDTO(post);
  }

  @Override
  public void delete(long id) {
    Post entity = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
    postRepository.delete(entity);
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
