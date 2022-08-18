package com.nangman.api.service;

import com.nangman.api.dto.SocketDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface BusService {
    void followBuses();
    public SocketDto.SubBusStop createSubBusStop(long routeId, int currentOrder);
    SocketDto.SubBusStop getCurrentBusStop(String sessionId);
}
