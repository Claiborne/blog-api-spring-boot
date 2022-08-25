package claiborne.blog.api.springboot.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;

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

  public StringBuilder display() {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/prac.txt"));
      writer.write("Text in a file");
      writer.write("\nSecond line");
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    StringBuilder s = new StringBuilder("");
    try {
      String line;
      BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/prac.txt"));
      while ((line = reader.readLine()) != null) {
        s.append(line);
        s.append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("********");
    System.out.println(s);
    return s;
  }
}
