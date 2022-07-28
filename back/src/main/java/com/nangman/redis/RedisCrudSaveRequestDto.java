package com.nangman.redis;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RedisCrudSaveRequestDto {

    private Long id;
    private String description;
    private LocalDateTime updatedAt;

    @Builder
    public RedisCrudSaveRequestDto(Long id, String description, LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.updatedAt = updatedAt;
    }

    public RedisCrud toRedisHash() {
        return RedisCrud.builder()
                .id(id)
                .description(description)
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
