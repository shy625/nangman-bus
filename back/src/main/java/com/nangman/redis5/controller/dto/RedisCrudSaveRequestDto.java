package com.nangman.redis5.controller.dto;

import com.nangman.redis5.domain.RedisCrud;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RedisCrudSaveRequestDto {

    private Long id;
    private String description;
    private String updateAt;

    @Builder
    public RedisCrudSaveRequestDto(Long id, String description, String updateAt) {
        this.id = id;
        this.description = description;
        this.updateAt = updateAt;
    }

    public RedisCrud toRedisHash() {
        return RedisCrud.builder()
                .id(id)
                .description(description)
                .updatedAt(updateAt)
                .build();
    }
}
