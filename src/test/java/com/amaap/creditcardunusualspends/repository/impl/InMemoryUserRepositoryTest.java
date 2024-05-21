package com.amaap.creditcardunusualspends.repository.impl;

import com.amaap.creditcardunusualspends.module.UserModule;
import com.amaap.creditcardunusualspends.repository.UserRepository;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryUserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(new UserModule());
        userRepository = injector.getInstance(UserRepository.class);
    }

    @Test
    void shouldBeAbleToAddUser() {
        // arrange
        int id = 1;
        String name = "John Doe";
        String email = "john.doe@example.com";

        // act
        userRepository.addUser(id, name, email);

        // assert
        Map<Integer, Map<String, String>> userData = userRepository.getUserData();
        assertTrue(userData.containsKey(id));
        assertEquals(name, userData.get(id).get("name"));
        assertEquals(email, userData.get(id).get("email"));
    }

    @Test
    void shouldBeAbleToRetrieveUserData() {
        // arrange
        int id1 = 1;
        String name1 = "John Doe";
        String email1 = "john.doe@example.com";

        int id2 = 2;
        String name2 = "Jane Smith";
        String email2 = "jane.smith@example.com";

        userRepository.addUser(id1, name1, email1);
        userRepository.addUser(id2, name2, email2);

        // act
        Map<Integer, Map<String, String>> userData = userRepository.getUserData();

        // assert
        assertEquals(2, userData.size());
        assertTrue(userData.containsKey(id1));
        assertEquals(name1, userData.get(id1).get("name"));
        assertEquals(email1, userData.get(id1).get("email"));
        assertTrue(userData.containsKey(id2));
        assertEquals(name2, userData.get(id2).get("name"));
        assertEquals(email2, userData.get(id2).get("email"));
    }

    @Test
    void shouldBeAbleToHandleEmptyDatabase() {
        // act
        Map<Integer, Map<String, String>> userData = userRepository.getUserData();

        // assert
        assertTrue(userData.isEmpty());
    }
}
