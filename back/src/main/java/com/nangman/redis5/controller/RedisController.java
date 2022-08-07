package com.nangman.redis5.controller;

import com.nangman.redis5.dto.RedisCrudSaveRequestDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
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

    @GetMapping("/test01/{key, subKey}")
    public String test01(@PathVariable("key") String key, @PathVariable("subKey") String subKey) {
        // 키의 서브키만 가져오는 방법
        return (String) redisTemplate.opsForHash().get(key, subKey);
//        Set<String> keys = repositoryConfig.redisTemplate().opsForSet().members();
    }

    @GetMapping("/test02")
    public String test02() {
        // 키와 서브키로 값을 가져오는 방법
        return (String) redisTemplate.opsForHash().get("1", "1");
//        Set<String> keys = repositoryConfig.redisTemplate().opsForSet().members();
    }

    @GetMapping("/test03")
    public void test03() {
        // 키의 서브키와 그 값을 지우는 방법
        redisTemplate.opsForHash().delete("1", "1");
//        Set<String> keys = repositoryConfig.redisTemplate().opsForSet().members();
    }

    @GetMapping("/test04")
    public void test04() {
        // 키의 서브키의  값을 증가,감소한다
        redisTemplate.opsForHash().increment("3", "sub1", 1);
//        Set<String> keys = repositoryConfig.redisTemplate().opsForSet().members();
    }

    @GetMapping("/test05")
    public Set<Object> test05() {
        // key에 존재하는 모든 value의 값들을 반환, subKey는 안씀
        return redisTemplate.opsForHash().keys("1");
//        Set<String> keys = repositoryConfig.redisTemplate().opsForSet().members();
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
