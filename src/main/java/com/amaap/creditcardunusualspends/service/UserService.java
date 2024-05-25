package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.service.exception.*;
import com.amaap.creditcardunusualspends.service.validator.UserValidator;
import com.google.inject.Inject;

public class UserService {
    UserValidator userValidator = new UserValidator();
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(int userId, String name, String email) throws CreditCardException {
        if (userId <= 0) {
            throw new InvalidUserIdException("Invalid Id: " + userId);
        }

        if (!userValidator.isValidName(name)) {
            throw new InvalidUserNameException("Invalid Name: " + name);
        }

        if (!userValidator.isValidEmail(email)) {
            throw new InvalidEmailException("Invalid Email: " + email);
        }
        if (userRepository.getUserData().containsKey(userId)) {
            throw new DuplicateUserIdException("Duplicate User ID: " + userId);
        }

        userRepository.addUser(userId, name, email);
        return true;
    }
}
