package com.nangman.redis5.controller.dto;

import com.nangman.redis5.domain.RedisCrud;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RedisCrudResponseDto {
    private String key;
    private String subKey;
    private String value;

    public RedisCrudResponseDto(RedisCrud redisHash) {
        this.key = redisHash.getKey();
        this.subKey = redisHash.getSubKey();
        this.value = redisHash.getValue();

    }
}
