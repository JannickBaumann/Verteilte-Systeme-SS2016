package rest_calls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		try {
			SpringApplication.run(Application.class, args);
			System.out.println("Ready!");
		} catch (Exception e) {
			System.out.println("Error!\n"+e.getMessage());
		}
	}
}