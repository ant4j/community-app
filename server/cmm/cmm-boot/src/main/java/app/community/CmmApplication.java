package app.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { 
		"app.community.self.controller", 
		"app.community.self.service",
		"app.community.collection.controller", 
		"app.community.collection.service", 
		"app.community.content.controller", 
		"app.community.content.service" 
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
public class CmmApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CmmApplication.class, args);
	}

}
