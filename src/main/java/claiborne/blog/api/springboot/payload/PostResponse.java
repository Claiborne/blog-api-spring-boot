package claiborne.blog.api.springboot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

  private List<PostDto> content;
  private int page;
  private int count;
  private long totalElements;
  private int totalPages;
  private boolean last;
}
