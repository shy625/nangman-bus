package com.nangman.api.service;

import com.nangman.api.dto.UserDto;
import com.nangman.config.JpaConfig;
import com.nangman.db.entity.Nickname;
import com.nangman.db.entity.User;
import com.nangman.db.repository.NicknameRepository;
import com.nangman.db.repository.SettingRepository;
import com.nangman.db.repository.UserRepository;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;


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


//    @Test
//    public void contextLoads() {
//        Assertions.assertNotNull(em);
//    }

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


        // then
        Assertions.assertNotNull(successUser);
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            User failUser = userService.getUserByUserId(3);
        });

    }


    @DisplayName("Useremail로 유저 조회 ")
    @Test
    void getUserByUseremail() {
        // given
        UserDto.RegisterRequest registerRequest = new UserDto.RegisterRequest("demo-user@email.com", passwordEncoder.encode("1111"), "1995-11-15");

        // when
        User resultUser = userService.createUser(registerRequest);
        User successUser = userService.getUserByUseremail(resultUser.getUseremail());


        // then
        Assertions.assertNotNull(successUser);
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            User failUser = userService.getUserByUseremail("nangman@naver.com");
        });
    }

    @DisplayName("유저 soft delete 성공")
    @Test
    void deleteUser() {
        // given
        UserDto.RegisterRequest registerRequest = new UserDto.RegisterRequest("demo-user@email.com", passwordEncoder.encode("1111"), "1995-11-15");

        // when
        User resultUser = userService.createUser(registerRequest);
        userService.deleteUser(resultUser.getId());

        // then
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            User failUser = userService.getUserByUseremail("demo-user@email.com");
        });
    }


    @Test
    @DisplayName("유저 정보(생일) 업데이트 성공")
    void updateUser() {
        // given
        UserDto.RegisterRequest registerRequest = new UserDto.RegisterRequest("demo-user@email.com", passwordEncoder.encode("1111"), "");
        UserDto.RegisterRequest updateRequest = new UserDto.RegisterRequest("demo-user@email.com", passwordEncoder.encode("1111"), "1995-11-15");

        // when
        User resultUser = userService.createUser(registerRequest);

        // then
        Assertions.assertEquals("", resultUser.getUserBirthday());
        log.info(resultUser.toString());

        // when
        User updateUser = userService.updateUser(updateRequest);
        log.info(resultUser.toString());

        // then
        Assertions.assertEquals("1995-11-15", updateUser.getUserBirthday());

    }

    @Test
    @DisplayName("유저 닉네임 룰렛 여부 업데이트 성공")
    void updateUserIsRouletted() {
        // given
        UserDto.RegisterRequest registerRequest = new UserDto.RegisterRequest("demo-user@email.com", passwordEncoder.encode("1111"), "");

        // when
        User resultUser = userService.createUser(registerRequest);

        // then
        Assertions.assertEquals(false, resultUser.isRouletted());

        // when
        User updateUser = userService.updateUserIsRouletted(resultUser.getId());

        // then
        Assertions.assertEquals(true, updateUser.isRouletted());

        // when
        updateUser = userService.updateUserIsRouletted(resultUser.getId());

        // then
        Assertions.assertEquals(false, updateUser.isRouletted());
    }

    @Test
    @DisplayName("유저 닉네임 랜덤 매칭")
    void updateUserNickname() {
        // given
        Nickname nickname1 = new Nickname();
        nickname1.setNickname("가우르_구라");
        Nickname nickname2 = new Nickname();
        nickname2.setNickname("구라_가우르");
        Nickname nickname3 = new Nickname();
        nickname3.setNickname("프론트장인_이우현");
        Nickname nickname4 = new Nickname();
        nickname4.setNickname("프론트_소현영");
        Nickname nickname5 = new Nickname();
        nickname5.setNickname("프론트_조무사");

        nicknameRepository.save(nickname1);
        nicknameRepository.save(nickname2);
        nicknameRepository.save(nickname3);
        nicknameRepository.save(nickname4);
        nicknameRepository.save(nickname5);

        User user1 = new User();
        user1.setUseremail("demo1@naver.com");
        user1.setPassword(passwordEncoder.encode("1111"));
        userRepository.save(user1);

        User user2 = new User();
        user2.setUseremail("demo2@naver.com");
        user2.setPassword(passwordEncoder.encode("1111"));
        userRepository.save(user2);

        // when
        userService.updateUserNickname();

        // then
        log.info(user1.getNickname().getNickname());
        log.info(user2.getNickname().getNickname());
        Assertions.assertNotNull(user1.getNickname());
        Assertions.assertNotNull(user1.getNickname());
        Assertions.assertNotNull(user2.getNickname());

    }
}