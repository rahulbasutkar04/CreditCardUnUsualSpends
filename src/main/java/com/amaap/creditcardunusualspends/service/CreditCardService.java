package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumber;
import com.amaap.creditcardunusualspends.service.exception.InvalidCreditCardNumberLength;

public class CreditCardService {
    UserRepository userRepository;
    public CreditCardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public boolean CreteCard(long creditCardNumber) throws InvalidCreditCardNumber, InvalidCreditCardNumberLength {
          if(creditCardNumber<=0) throw new InvalidCreditCardNumber("Invalid Credit card number.");
          String creditCardLength=Long.toString(creditCardNumber);
          if(creditCardLength.length()!=8) throw  new InvalidCreditCardNumberLength("Credit card length must be 8");
          userRepository.getUserId();

        return  true;
    }
}
