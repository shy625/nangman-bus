package com.nangman.redis5.service;

import com.nangman.redis5.controller.dto.RedisCrudResponseDto;
import com.nangman.redis5.controller.dto.RedisCrudSaveRequestDto;
import com.nangman.redis5.domain.RedisCrud;
import com.nangman.redis5.domain.RedisCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class RedisCrudService {
    private final RedisCrudRepository redisCrudRepository;

    @Transactional
    public Long save(RedisCrudSaveRequestDto requestDto) {
        return redisCrudRepository.save(requestDto.toRedisHash()).getId();
    }

    public RedisCrudResponseDto get(Long id) {
        RedisCrud redisCrud = redisCrudRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nothing saved id=" +id));
        return new RedisCrudResponseDto(redisCrud);
    }

    public Set<String> getAll() {
        Iterable<RedisCrud> list = redisCrudRepository.findAll();
        Set<String> keys = null;
        for(RedisCrud str : list) {
            keys.add(str.getId().toString());
        }
        return keys;
    }

}
