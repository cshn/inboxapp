package io.javabrains.inbox.email;

import io.javabrains.inbox.emaillist.EmailListItem;
import io.javabrains.inbox.emaillist.EmailListItemKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface EmailRepository extends CassandraRepository<Email, UUID> {

}
