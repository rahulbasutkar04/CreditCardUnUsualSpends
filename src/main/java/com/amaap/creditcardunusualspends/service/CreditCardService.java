package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.repository.CreditCardRepository;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumber;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumberLength;

public class CreditCardService {
    UserRepository userRepository;
    CreditCardRepository creditCardRepository;
    public CreditCardService(UserRepository userRepository,CreditCardRepository creditCardRepository) {
        this.userRepository = userRepository;
        this.creditCardRepository=creditCardRepository;
    }

    public boolean CreateCard(long creditCardNumber) throws InvalidCreditCardNumber, InvalidCreditCardNumberLength {
          if(creditCardNumber<=0) throw new InvalidCreditCardNumber("Invalid Credit card number.");
          String creditCardLength=Long.toString(creditCardNumber);
          if(creditCardLength.length()!=8) throw  new InvalidCreditCardNumberLength("Credit card length must be 8");
          creditCardRepository.addCreditCardDetails(userRepository.getUserId(),creditCardNumber);
        return  true;
    }
}
