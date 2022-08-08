package claiborne.blog.api.springboot.payload;

import lombok.Data;

@Data
public class CommentDto {

  private long id;
  private String name;
  private String body;
}
