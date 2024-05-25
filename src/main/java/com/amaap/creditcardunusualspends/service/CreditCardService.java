package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.service.exception.DuplicateCreditCardException;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumber;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumberLength;

import java.util.Map;

public class CreditCardService {
    UserRepository userRepository;
    CreditCardRepository creditCardRepository;

    public CreditCardService(UserRepository userRepository, CreditCardRepository creditCardRepository) {
        this.userRepository = userRepository;
        this.creditCardRepository = creditCardRepository;
    }

    public boolean CreateCard(long creditCardNumber) throws InvalidCreditCardNumber, InvalidCreditCardNumberLength, DuplicateCreditCardException {
        if (creditCardNumber <= 0) throw new InvalidCreditCardNumber("Invalid Credit card number.");
        String creditCardLength = Long.toString(creditCardNumber);
        if (creditCardLength.length() != 8) throw new InvalidCreditCardNumberLength("Credit card length must be 8");

        Map<Integer, Long> creditCardDetails = creditCardRepository.getCreditCardDetails();
        if (creditCardDetails.containsValue(creditCardNumber)) {
            throw new DuplicateCreditCardException("Duplicate Credit Card Number: " + creditCardNumber);
        }
        creditCardRepository.addCreditCardDetails(userRepository.getUserId(), creditCardNumber);

        if (creditCardRepository.getCreditCardDetails().size() != 0) {
            System.out.println("Credit card with ID Number is saved!!");
            return true;

        } else return false;
    }
}
