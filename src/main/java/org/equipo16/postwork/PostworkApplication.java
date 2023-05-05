package org.equipo16.postwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PostworkApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(PostworkApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
