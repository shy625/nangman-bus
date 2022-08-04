package com.nangman.redis5.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@Getter
@RedisHash("redisCrud")
public class RedisCrud implements Serializable {

    @Id
    private Long id;
    private String description;
    private String updatedAt;

    @Builder
    public RedisCrud(Long id, String description, String updatedAt) {
        this.id = id;
        this.description = description;
        this.updatedAt = updatedAt;
    }

//    public void update(String description, String updatedAt) {
//        if(updatedAt.isAfter(this.updatedAt)) {
//            this.description = description;
//            this.updatedAt = updatedAt;
//        }
//    }

}
