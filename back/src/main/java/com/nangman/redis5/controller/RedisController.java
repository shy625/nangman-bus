package com.nangman.redis5.controller;

import com.nangman.redis5.config.RedisProperties;
import com.nangman.redis5.config.RedisRepositoryConfig;
import com.nangman.redis5.controller.dto.RedisCrudResponseDto;
import com.nangman.redis5.controller.dto.RedisCrudSaveRequestDto;
import com.nangman.redis5.service.RedisCrudService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController("api/redis")
public class RedisController {
    private final RedisCrudService redisCrudService;
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
    public Long save(@RequestBody RedisCrudSaveRequestDto requestDto) {
        log.info(">>>>>>>>>>>>>>>>>>>>>> [save] redisCrud={}", requestDto);
        redisTemplate.opsForHash().put(requestDto.getId().toString(), requestDto.getDescription(), requestDto.getUpdateAt());
        return redisCrudService.save(requestDto);
    }

    @GetMapping("/get/{id}")
    public RedisCrudResponseDto get(@PathVariable Long id) {
        return redisCrudService.get(id);
    }

    @GetMapping("/delete/{key}")
    public void delete(@PathVariable String key) {
        redisTemplate.delete(key);
    }

}
