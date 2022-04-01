package io.javabrains.inbox;

import io.javabrains.inbox.email.EmailService;
import io.javabrains.inbox.folders.DataStaxAstraProperties;
import io.javabrains.inbox.folders.Folder;
import io.javabrains.inbox.folders.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

@SpringBootApplication
@ConfigurationPropertiesScan
@RestController
public class InboxApp {

	@Autowired
	FolderRepository folderRepository;
	@Autowired
	EmailService emailService;

	private final String FILE_NAME = "src/main/resources/secure-connect.zip";

	public static void main(String[] args) {
		SpringApplication.run(InboxApp.class, args);
	}

	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		File securityConnectBundle = new File(FILE_NAME);
		Path bundle = securityConnectBundle.toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}

	@PostConstruct
	public void init() {
		folderRepository.save(new Folder("cshn", "Work", "blue"));
		folderRepository.save(new Folder("cshn", "Home", "green"));
		folderRepository.save(new Folder("cshn", "Family", "yellow"));
		folderRepository.save(new Folder("cshnqq", "Work", "blue"));
		folderRepository.save(new Folder("cshnqq", "Home", "green"));
		folderRepository.save(new Folder("cshnqq", "Family", "yellow"));

		for (int i = 0; i < 10; i++) {
			emailService.sendEmail("cshn", Arrays.asList("cshn", "abc"), "Hello " + i, "body " + i);
		}

		emailService.sendEmail("abc", Arrays.asList("def", "abc"), "Hello ", "body ");
	}
}
