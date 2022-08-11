package com.nangman.api.service;

import com.nangman.api.dto.BusstopDto;
import com.nangman.db.entity.Busstop;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface BusstopService {
    @Transactional
    List<Busstop> addBusstop(BusstopDto.Request request);
    List<Busstop> getBusstopByCode(String code);
    void deleteBusstopByCode(String code);
}
