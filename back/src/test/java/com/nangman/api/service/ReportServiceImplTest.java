package com.nangman.api.service;

import com.nangman.config.JpaConfig;
import com.nangman.db.repository.*;
import lombok.extern.slf4j.Slf4j;
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

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaConfig.class)
class ReportServiceImplTest {

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

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ChatInOutRecordRepository chatInOutRecordRepository;

    ReportService reportService;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserServiceImpl(userRepository, passwordEncoder, settingRepository, nicknameRepository);
//        reportService = new ReportServiceImpl();

        log.info("====== test start ======");
    }

    @DisplayName("유저 Id report 조회")
    @Test
    void getReportsByUserId() {

    }

    @Test
    void getReportByIds() {
    }
}