package app.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "app.community.community.application.adapter, app.community.community.infrastructure.jpa.adapter, app.community.community.infrastructure.controller")
@EnableJpaRepositories("app.community.community.infrastructure.jpa.repository")
@EntityScan("app.community.community.infrastructure.jpa.entity")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
