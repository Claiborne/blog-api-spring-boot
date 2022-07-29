package claiborne.blog.api.springboot.repository;

import claiborne.blog.api.springboot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
