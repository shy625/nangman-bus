package com.nangman.api.service;

import com.nangman.api.dto.SocketDto;
import org.springframework.stereotype.Service;

@Service
public interface BusService {
    void followBuses();
    public SocketDto.SubBusStop createSubBusStop(long routeId, int currentOrder);
}
