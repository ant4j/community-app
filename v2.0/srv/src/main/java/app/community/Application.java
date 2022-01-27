package app.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { 
		"app.community.self.controller", 
		"app.community.self.handler",
		"app.community.collection.controller", 
		"app.community.collection.handler", 
		"app.community.content.controller", 
		"app.community.content.handler" 
})
@EnableJpaRepositories(basePackages = { 
		"app.community.self.persistence.repository", 
		"app.community.collection.persistence.repository", 
		"app.community.content.persistence.repository" 
})
@EntityScan(basePackages = { 
		"app.community.self.persistence.model", 
		"app.community.collection.persistence.model", 
		"app.community.content.persistence.model" 
})
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
