package com.amaap.creditcardunusualspends.service;

import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.amaap.creditcardunusualspends.repository.impl.InMemoryUserRepository;
import com.amaap.creditcardunusualspends.service.exception.InvalidEmailException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserIdException;
import com.amaap.creditcardunusualspends.service.exception.InvalidUserNameException;
import com.amaap.creditcardunusualspends.service.validator.UserValidator;
import com.google.inject.Inject;

public class UserService {
    private final UserValidator userValidator;
    private final UserRepository userRepository;

    @Inject
    public UserService(UserValidator userValidator, UserRepository userRepository) {
        this.userValidator = userValidator;
        this.userRepository = userRepository;
    }

    public boolean createUser(int userId, String name, String email) throws InvalidUserIdException, InvalidUserNameException, InvalidEmailException {
        if (userId <= 0) {
            throw new InvalidUserIdException("Invalid Id: " + userId);
        }

        if (!userValidator.isValidName(name)) {
            throw new InvalidUserNameException("Invalid Name: " + name);
        }

        if (!userValidator.isValidEmail(email)) {
            throw new InvalidEmailException("Invalid Email: " + email);
        }

        userRepository.addUser(userId, name, email);
        return true;
    }
}
