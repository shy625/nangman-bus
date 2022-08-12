package com.nangman.api.service;

import com.nangman.api.dto.RouteDto;
import com.nangman.db.entity.Route;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface RouteService {

    @Transactional
    Route followBus(RouteDto.Request request);
    @Transactional
    String unfollowBus(RouteDto.Request request);
    Route getRoute(RouteDto.Request request);
    List<Route> getAll();
}
