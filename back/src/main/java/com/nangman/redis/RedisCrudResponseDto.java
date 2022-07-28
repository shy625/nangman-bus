package com.nangman.redis;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RedisCrudResponseDto {

    private Long id;
    private String description;
    private LocalDateTime updatedAt;

    public RedisCrudResponseDto(RedisCrud redisHash) {
        this.id = redisHash.getId();
        this.description = redisHash.getDescription();
        this.updatedAt = redisHash.getUpdatedAt();
    }
}
