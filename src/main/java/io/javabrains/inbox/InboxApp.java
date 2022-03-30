package io.javabrains.inbox;

import io.javabrains.inbox.folders.DataStaxAstraProperties;
import io.javabrains.inbox.folders.Folder;
import io.javabrains.inbox.folders.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.nio.file.Path;

@SpringBootApplication
@ConfigurationPropertiesScan
@RestController
public class InboxApp {

	@Autowired
	FolderRepository folderRepository;

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
		folderRepository.save(new Folder("cshn", "Inbox", "blue"));
		folderRepository.save(new Folder("cshn", "Sent", "green"));
		folderRepository.save(new Folder("cshn", "Important", "yellow"));
	}
	

}
