package com.nangman.api.service;

import com.nangman.api.dto.SocketDto;
import org.springframework.stereotype.Service;

@Service
public interface SocketService {

    SocketDto.SubBusStop getCurrentBusStop(String sessionId);

}
