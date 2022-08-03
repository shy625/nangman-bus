package com.nangman.redis5.controller.dto;

import com.nangman.redis5.domain.RedisCrud;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RedisCrudResponseDto {
    private Long id;
    private String description;
    private LocalDateTime updateAt;

    public RedisCrudResponseDto(RedisCrud redisHash) {
        this.id = redisHash.getId();
        this.description = redisHash.getDescription();
        this.updateAt = redisHash.getUpdatedAt();

    }
}
