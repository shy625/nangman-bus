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
    private String key;
    private String subKey;
    private String value;

    @Builder
    public RedisCrud(String key, String subKey, String value) {
        this.key = key;
        this.subKey = subKey;
        this.value = value;
    }

//    public void update(String description, String updatedAt) {
//        if(updatedAt.isAfter(this.updatedAt)) {
//            this.description = description;
//            this.updatedAt = updatedAt;
//        }
//    }

}
