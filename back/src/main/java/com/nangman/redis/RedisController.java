package com.nangman.redis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RedisController {

    private final RedisCrudService redisCrudService;
    private final StringRedisTemplate redisTemplate;

    @GetMapping("/redis")
    public String ok() {
        return "ok";
    }

    @GetMapping("/redis/keys")
    public String keys() {
        Set<String> keys = redisTemplate.opsForSet().members("*");
        assert keys != null;
        return Arrays.toString(keys.toArray());
    }

    @PostMapping("/redis/save")
    public Long save(@RequestBody RedisCrudSaveRequestDto requestDto) {
        log.info(">>>>>>>>>>>>>> [save] redisCrud={}", requestDto);
        return redisCrudService.save(requestDto);
    }

    @GetMapping("/redis/get/{id}")
    public RedisCrudResponseDto get(@PathVariable Long id) {
        return redisCrudService.get(id);
    }
}
