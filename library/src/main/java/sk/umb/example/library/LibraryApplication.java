package sk.umb.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {
	///TODO http://localhost:8080/swagger-ui/index.html rozhranie swaggeru
	public static void main(String[] args) {
		System.out.println("Hello world");
		SpringApplication.run(LibraryApplication.class, args);
	}

}
