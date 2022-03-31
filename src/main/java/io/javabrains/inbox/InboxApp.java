package io.javabrains.inbox;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import io.javabrains.inbox.email.Email;
import io.javabrains.inbox.email.EmailRepository;
import io.javabrains.inbox.emaillist.EmailListItem;
import io.javabrains.inbox.emaillist.EmailListItemKey;
import io.javabrains.inbox.emaillist.EmailListItemRepository;
import io.javabrains.inbox.folders.DataStaxAstraProperties;
import io.javabrains.inbox.folders.Folder;
import io.javabrains.inbox.folders.FolderRepository;
import io.javabrains.inbox.folders.UnreadEmailStatsRepository;
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
import java.util.Arrays;

@SpringBootApplication
@ConfigurationPropertiesScan
@RestController
public class InboxApp {

	@Autowired
	FolderRepository folderRepository;
	@Autowired
	EmailListItemRepository emailListItemRepository;
	@Autowired
	EmailRepository emailRepository;
	@Autowired
	UnreadEmailStatsRepository unreadEmailStatsRepository;

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

		unreadEmailStatsRepository.incrementUnreadCounter("cshn", "Inbox");
		unreadEmailStatsRepository.incrementUnreadCounter("cshn", "Inbox");
		unreadEmailStatsRepository.incrementUnreadCounter("cshn", "Inbox");

		for (int i = 0; i < 10; i++) {
			EmailListItemKey key = new EmailListItemKey();
			key.setId("cshn");
			key.setLabel("Inbox");
			key.setTimeUUID(Uuids.timeBased());

			EmailListItem item = new EmailListItem();
			item.setKey(key);
			item.setTo(Arrays.asList("cshn", "abc", "dafad"));
			item.setSubject("Subject " + i);
			item.setUnread(true);
			emailListItemRepository.save(item);

			Email email = new Email();
			email.setId(key.getTimeUUID());
			email.setFrom("cshn");
			email.setSubject(item.getSubject());
			email.setBody("Body " + i);
			email.setTo(item.getTo());
			emailRepository.save(email);
		}
	}
	

}
