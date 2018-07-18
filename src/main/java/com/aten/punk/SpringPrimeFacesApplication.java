package com.aten.punk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringPrimeFacesApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringPrimeFacesApplication.class, args);
  }
}
