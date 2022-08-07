package com.nangman.api.service;

import com.nangman.api.dto.UserDto;
import com.nangman.config.JpaConfig;
import com.nangman.db.entity.User;
import com.nangman.db.repository.NicknameRepository;
import com.nangman.db.repository.SettingRepository;
import com.nangman.db.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.InvalidClassException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaConfig.class)
class UserServiceImplTest {

    UserServiceImpl userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    NicknameRepository nicknameRepository;

    @Autowired
    SettingRepository settingRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    TestEntityManager em;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, passwordEncoder, settingRepository, nicknameRepository);
        log.info("====== test start ======");
    }


    @Test
    public void contextLoads() {
        Assertions.assertNotNull(em);
    }

    @DisplayName("유저_생성_성공")
    @Test
    void createValidUser() {
        // given
        UserDto.RegisterRequest registerRequest = new UserDto.RegisterRequest("demo-user@email.com", passwordEncoder.encode("1111"), "1995-11-15");
        UserDto.RegisterRequest registerRequestWithoutBirthday = new UserDto.RegisterRequest("demo1-user@email.com", passwordEncoder.encode("1111"), "");

        // when
        User successUser = userService.createUser(registerRequest);
        User successUserWithoutBirthday = userService.createUser(registerRequestWithoutBirthday);

        // then
        Assertions.assertNotNull(successUser);
        Assertions.assertNotNull(successUserWithoutBirthday);
    }

    @DisplayName("유저 생성 실패")
    @Test
    void createInvaildUser() {
        // given
        UserDto.RegisterRequest registerRequestError = new UserDto.RegisterRequest("demo@naver.com", null, "");

        // when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(registerRequestError);
        });
    }

    @DisplayName("ID로 유저 조회")
    @Test
    void getUserByserId() {
        // given
        UserDto.RegisterRequest registerRequest = new UserDto.RegisterRequest("demo-user@email.com", passwordEncoder.encode("1111"), "1995-11-15");

        // when
        User resultUser = userService.createUser(registerRequest);
        User successUser = userService.getUserByUserId(resultUser.getId());
        User failUser = userService.getUserByUserId(3);

        // then
        Assertions.assertNotNull(successUser);
        Assertions.assertNull(failUser.);
    }


    @DisplayName("Useremail로 유저 조회 성공")
    @Test
    void getUserByUseremail() {
    }

    @DisplayName("유저 soft delete 성공")
    @Test
    void deleteUser() {
    }


    @Test
    void updateUser() {
    }

    @Test
    void updateUserIsRouletted() {
    }

    @Test
    void updateUserNickname() {
    }
}