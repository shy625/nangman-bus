package com.nangman.redis5.service;

import com.nangman.db.entity.Bus;
import com.nangman.redis5.config.RedisRepositoryConfig;
import com.nangman.redis5.dto.chatLogDto;
import com.nangman.redis5.dto.chattingRoomDto;
import com.nangman.redis5.dto.roomUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{
    private final StringRedisTemplate redisTemplate;


    public void test01() {
        redisTemplate.opsForHash().put("key1", "subKey1", "hello");
    }

    @Override
    public void updateBudData(String roomId, Bus bus) {
    }

    @Override
    public void createChattingRoom(String roomId, Bus bus, List<String> route) {

    }

    @Override
    public chatLogDto deleteChattingRoom(String roomId) {
        return null;
    }

    @Override
    public List<chattingRoomDto> selectRooms(double lat, double lng) {
        return null;
    }

    @Override
    public boolean isAccessibleRoom(double lat, double lng) {
        return false;
    }

    @Override
    public void upLike(String roomId, String chatId) {

    }

    @Override
    public void downLike(String roomId, String chatId) {

    }

    @Override
    public void updateMyEmotion(String roomId, String userId, int emotion) {

    }

    @Override
    public List<roomUserDto> roomUserList(String roomId) {
        return null;
    }

    @Override
    public void createChat(String roomId, String userId, String chatId, String CreatedTime, String chat) {

    }

    @Override
    public void joinRoom(String roomId, String userId, String inTime) {

    }

    @Override
    public void exitRoom(String roomId, String userId, String outTime) {

    }
}
