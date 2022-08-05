package com.nangman.api.service;

import com.nangman.config.JpaConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaConfig.class)
@Slf4j
class UserServiceTest {

    @BeforeEach
    void setUp() {
        log.info("Start userServcie Test");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createUser() {
    }

    @Test
    void getUserByUserId() {
    }

    @Test
    void getUserByUseremail() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void updateUserIsRouletted() {
    }
}