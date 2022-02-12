package app.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { 
		"app.community.restapi.self.controller", 
		"app.community.business.self.service",
		"app.community.restapi.collection.controller", 
		"app.community.business.collection.service", 
		"app.community.restapi.content.controller", 
		"app.community.business.content.service" 
})
@EnableJpaRepositories(basePackages = { 
		"app.community.persistence.self.repository", 
		"app.community.persistence.collection.repository", 
		"app.community.persistence.content.repository" 
})
@EntityScan(basePackages = { 
		"app.community.persistence.self.model", 
		"app.community.persistence.collection.model", 
		"app.community.persistence.content.model" 
})
public class CmmApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CmmApplication.class, args);
	}

}
