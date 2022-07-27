package com.nangman.redis;

import com.nangman.db.entity.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisPublisher {
    private final RedisTemplate<String, Object> redisTemplate;

    // Notice는 52의 채팅DTO 낭만버스의 채팅 규격을 만들어야함함
   public void publish(ChannelTopic topic, Chat chat) {
        redisTemplate.convertAndSend(topic.getTopic(), chat);
    }
}
