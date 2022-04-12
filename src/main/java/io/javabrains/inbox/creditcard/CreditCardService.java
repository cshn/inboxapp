package io.javabrains.inbox.creditcard;

import io.javabrains.inbox.folders.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public void createDefaultCreditCards(String userId) {
        creditCardRepository.save(new CreditCard(userId, "Citibank", "Cashback", "Mastercard", 800,
                Arrays.asList("Dining", "Groceries", "Petrol")));
        creditCardRepository.save(new CreditCard(userId, "OCBC", "Frank", "VISA", 600,
                Arrays.asList("Online", "Smartphone")));
        creditCardRepository.save(new CreditCard(userId, "Amex", "True Cashback", "Amex", 0,
                Arrays.asList("Everything")));
    }
}
