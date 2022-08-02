package com.nangman.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RedisCrudService {
    private final RedisCrudRepository redisCrudRepository;

    @Transactional
    public Long save(RedisCrudSaveRequestDto requestDto) {
        return redisCrudRepository.save(requestDto.toRedisHash()).getId();
    }

    public RedisCrudResponseDto get(Long id) {
        RedisCrud redisCrud = redisCrudRepository.findById(id).orElseThrow(() -> new RuntimeException());
        return new RedisCrudResponseDto(redisCrud);
    }
}
