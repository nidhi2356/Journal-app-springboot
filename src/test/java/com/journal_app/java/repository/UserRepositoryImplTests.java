package com.journal_app.java.repository;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryImplTests {

    @Autowired
    private  UserRepositoryImpl userRepository;


    @Test
    void testSaveNewUser(){
        Assertions.assertNotNull(userRepository.getUserForSA());
    }


}
