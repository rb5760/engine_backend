package com.search.engine;

import com.search.engine.dao.PersonRepository;
import com.search.engine.model.Person;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.util.stream.Stream;

@SpringBootApplication
//@EnableResourceServer
public class EngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(EngineApplication.class, args);
	}

	@Bean
	ApplicationRunner init(PersonRepository repository) {
		return args -> {
			Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
				Person user = new Person();
				user.setName(name);
				user.setEmail(name.toLowerCase() + "@domain.com");
				user.setAdresse("Rue de l'europe 95500");
				repository.save(user);
			});


			repository.findAll().forEach(System.out::println);
		};
	}

}
