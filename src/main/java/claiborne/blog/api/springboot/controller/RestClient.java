package claiborne.blog.api.springboot.controller;
import claiborne.blog.api.springboot.service.impl.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/practice")
public class RestClient {

  private PracticeService practiceService;

  public RestClient(PracticeService practiceService) {
    this.practiceService = practiceService;
  }

  @GetMapping
  public String get() {
    return practiceService.get();
  }

  @GetMapping("/display")
  public StringBuilder display() { return practiceService.display(); }

}
