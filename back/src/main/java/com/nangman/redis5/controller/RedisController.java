package com.nangman.redis5.controller;

import com.nangman.redis5.dto.RedisCrudSaveRequestDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/redis")
@Api(value = "레디스 API", tags = {"redis"})
public class RedisController {
    private final StringRedisTemplate redisTemplate;
//    private final RedisRepositoryConfig repositoryConfig;

    @GetMapping("/")
    public String ok() {
        return "ok";
    }

    @GetMapping("/keys")
    public String keys() {
//        Set<String> keys = redisTemplate.opsForSet().members("*");
//        Set<String> keys = redisCrudService.getAll();
        Set<String> keys = redisTemplate.keys("*");
//        Set<String> keys = repositoryConfig.redisTemplate().opsForSet().members();
        assert keys != null;
        return Arrays.toString(keys.toArray());
    }

    @PostMapping("/save")
    public void save(@RequestBody RedisCrudSaveRequestDto requestDto) {
        log.info(">>>>>>>>>>>>>>>>>>>>>> [save] redisCrud={}", requestDto);
        redisTemplate.opsForHash().put(requestDto.getKey(), requestDto.getSubKey(), requestDto.getValue());
    }


    @GetMapping("/delete/{keyId}")
    public void del(@PathVariable String keyId) {
        redisTemplate.delete(keyId);
//        System.out.println(key);
    }

}
