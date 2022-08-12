package com.nangman.api.service;

import com.nangman.db.entity.Route;
import com.nangman.db.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BusServiceImpl implements BusService{

    private final String serviceKey = "7iPULnyfcY7QCbYSG6l7V1Xt%2BlMtgY1Svqhr%2BpqPNabuvA7obO6MIlH1n5e9BvZeA7oD52cRQTL5ToItgz99cg%3D%3D";
    private final int pageNo = 1;
    private final int numOfRows = 1000;
    private final String dataType = "json";

    private final RouteRepository routeRepository;
    @Override
    public void followBuses() {
        List<Route> routeList = routeRepository.findAll();

        for (Route route : routeList){

        }
    }
}
