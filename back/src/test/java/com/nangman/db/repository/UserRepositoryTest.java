package com.nangman.db.repository;

import com.nangman.config.JpaConfig;
import com.nangman.db.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaConfig.class)
@Slf4j
class UserRepositoryTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup(){
        log.info("Start userRepository Test");
    }

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(em);
    }

    @DisplayName("user save")
    @Test
    void saveTest() {

        log.info("=======save test start========");

        // given
        User user = new User();

        user.setUseremail("demo-user@email.com");
        user.setPassword("nangman");
        user.setUserBirthday("1111-11-11");

        // when, then
        Assertions.assertNull(user.getId());

        em.persist(user);
        Assertions.assertNotNull(user.getId());

        log.info("=======save test end========");
    }

    @DisplayName("find not deleted user by Id")
    @Test
    void findByIdAndIsDeletedFalseTest(){
        log.info("=======findByIdAndIsDeletedFalse test start========");
        // given
        User user = new User();

        user.setUseremail("demo-user@email.com");
        user.setPassword("nangman");
        user.setUserBirthday("1111-11-11");
        user.setDeleted(true);

        // when
        em.persist(user);

        // then
        Assertions.assertFalse(userRepository.findByIdAndIsDeletedFalse(user.getId()).isPresent());

        // given
        user.setDeleted(false);

        // then
        Assertions.assertNotNull(userRepository.findByIdAndIsDeletedFalse(user.getId()).get());

        log.info("=======findByIdAndIsDeletedFalse test end========");
    }

    @DisplayName("find not deleted user by useremail")
    @Test
    void findByUseremailAndIsDeletedFalse() {
        log.info("=======findByUseremailAndIsDeletedFalse test start========");

        // given
        User user = new User();

        user.setUseremail("demo-user@email.com");
        user.setPassword("nangman");
        user.setUserBirthday("1111-11-11");
        user.setDeleted(true);

        // when
        em.persist(user);

        // then
        Assertions.assertFalse(userRepository.findByUseremailAndIsDeletedFalse(user.getUseremail()).isPresent());

        // given
        user.setDeleted(false);

        // then
        Assertions.assertNotNull(userRepository.findByUseremailAndIsDeletedFalse(user.getUseremail()).get());
        log.info("=======findByUseremailAndIsDeletedFalse test end========");
    }
}
