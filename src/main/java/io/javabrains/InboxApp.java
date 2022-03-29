package io.javabrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
public class InboxApp {

	@Autowired
	private DataStaxAstraProperties astraProperties;

	public static void main(String[] args) {
		SpringApplication.run(InboxApp.class, args);
	}

	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer() {
		Path bundle = astraProperties.getSecurityConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}
	

}
