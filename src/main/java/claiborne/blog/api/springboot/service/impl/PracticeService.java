package claiborne.blog.api.springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.nio.file.Files;

@Service
public class PracticeService {

  private static final String URL = "https://reqres.in/api/unknown/2";

  private final RestTemplate restTemplate;

  public PracticeService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String get() {
    String response = restTemplate.getForObject(URL, String.class);
    System.out.println(response);
    return response;
  }
}
