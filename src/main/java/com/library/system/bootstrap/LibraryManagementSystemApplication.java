package com.library.system.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.library.system.*")
@EntityScan("com.library.system.Entity")
@EnableJpaRepositories("com.library.system.dao")
public class LibraryManagementSystemApplication {

  /*
   * Swagger UI 	- 	http://localhost:8080/swagger-ui.html
   *
   * Endpoints 	- 	    http://localhost:8080/v1/students
   * 				    http://localhost:8080/v1/books
   *
   * H2 DB 		- 	    http://localhost:8080/h2-console
   *
   * DB Name		-	Library
   */

  public static void main(String[] args) {
    SpringApplication.run(LibraryManagementSystemApplication.class, args);
  }
}
