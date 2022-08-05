package com.nangman.redis5.controller.dto;

import com.nangman.redis5.domain.RedisCrud;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RedisCrudSaveRequestDto {

    private String key;
    private String subKey;
    private String value;

    @Builder
    public RedisCrudSaveRequestDto(String key, String subKey, String value) {
        this.key = key;
        this.subKey = subKey;
        this.value = value;
    }

    public RedisCrud toRedisHash() {
        return RedisCrud.builder()
                .key(key)
                .subKey(subKey)
                .value(value)
                .build();
    }
}
