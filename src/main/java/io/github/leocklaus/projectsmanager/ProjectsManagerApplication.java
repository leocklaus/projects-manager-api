package io.github.leocklaus.projectsmanager;

import io.github.leocklaus.projectsmanager.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ProjectsManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectsManagerApplication.class, args);
	}

}
