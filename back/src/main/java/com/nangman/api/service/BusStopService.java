package com.nangman.api.service;

import com.nangman.db.entity.BusStop;
import com.nangman.db.entity.Route;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface BusStopService {
    @Transactional
    List<BusStop> addBusStop(Route route);
    List<BusStop> getBusStopByRouteId(long routeId);
    void deleteBusStopByRouteId(long routeId);
}
