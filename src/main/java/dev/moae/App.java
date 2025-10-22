package dev.moae;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

// TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

@SpringBootApplication
@RestController
public class App {
  @GetMapping("/")
  public String alive() {
    return "Server alive";
  }

  @GetMapping("/hello/{name}")
  public String hello(@PathVariable String name) {
    String test = "test";
    return "Hello " + name;
  }

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
