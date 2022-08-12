package com.nangman.api.service;

import com.nangman.api.dto.MainDto;
import org.springframework.stereotype.Service;

@Service
public interface MainService {
    MainDto.Info getMainPageData(long userId);
}
