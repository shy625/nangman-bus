package com.nangman.api.service;

import com.nangman.api.dto.RouteDto;
import com.nangman.db.entity.Route;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface RouteService {

    @Transactional
    RouteDto.Info followBus(RouteDto.Request request);
    @Transactional
    String unfollowBus(RouteDto.Request request);
    RouteDto.Info getRoute(String code);
    List<RouteDto.Info> getAll();
}
