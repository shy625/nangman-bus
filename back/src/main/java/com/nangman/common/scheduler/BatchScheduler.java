package com.nangman.common.scheduler;

import com.nangman.api.dto.ChatDto;
import com.nangman.api.service.BusService;
import com.nangman.api.service.ChatService;
import com.nangman.api.service.UserService;
import com.nangman.common.util.TimeCalculator;
import com.nangman.db.entity.Bus;

import com.nangman.db.repository.BusRepository;

import com.nangman.redis5.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Component
@Slf4j
@RequiredArgsConstructor
public class BatchScheduler {

    private final UserService userService;
    private final BusService busService;
    private final RedisService redisService;
    private final BusRepository busRepository;
    private final ChatService chatService;

    //10초마다 실행
    @Scheduled(cron = "0 0 4 * * *")
    public void nicknameSchedule() {
        userService.updateUserNickname();
    }

    @Scheduled(cron = "10 * * * * *")
    public void busLoggingSchedule() {
        busService.followBuses();
    }

    @Scheduled(cron = "50 * * * * *")
    @Transactional
    public void endPointCheckingSchedule(){
        List<Bus> busList = busRepository.findAll();
        for (Bus bus : busList){
            int isDone = TimeCalculator.getAccessTime(LocalDateTime.now(), bus.getLastModifiedDate());
            if (isDone > 3600){
                ChatDto.ChatLog chatLog = redisService.deleteChattingRoom(bus.getSessionId());
                chatService.InsertChatLogs(chatLog);
                bus.setSessionId(null);
                busRepository.save(bus);
            }
        }
    }

}