package io.javabrains.inbox.creditcard;

import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface CreditCardRepository extends CassandraRepository<CreditCard, String> {
    List<CreditCard> findAllById(String id);
}