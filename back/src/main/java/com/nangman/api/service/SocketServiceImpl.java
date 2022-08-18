package com.nangman.api.service;

import com.nangman.api.dto.RouteDto;
import com.nangman.api.dto.SocketDto;
import com.nangman.db.entity.Bus;
import com.nangman.db.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SocketServiceImpl implements SocketService {

    private final BusRepository busRepository;
    private final RouteService routeService;

    @Override
    public SocketDto.SubBusStop getCurrentBusStop(String sessionId) {
        Bus bus = busRepository.findBusBySessionId(sessionId).orElseThrow(IllegalAccessError::new);
        RouteDto.Info routeInfo = routeService.getRoute(bus.getCode());

        int curBusStopOrd = bus.getNodeOrd();
        List<RouteDto.BusStopInfo> busStopInfoList = routeInfo.getBusStopInfoList();
        SocketDto.SubBusStop curBusStopInfo = new SocketDto.SubBusStop();

        curBusStopInfo.setCurId(busStopInfoList.get(curBusStopOrd - 1).getBusStopId());
        curBusStopInfo.setCurName(busStopInfoList.get(curBusStopOrd - 1).getNodeName());
        if (curBusStopOrd == 1) {
            curBusStopInfo.setPrevId(0L);
            curBusStopInfo.setPrevName(null);
        } else if (curBusStopOrd == busStopInfoList.size()) {
            curBusStopInfo.setNextId(0L);
            curBusStopInfo.setNextName(null);
        } else {
            curBusStopInfo.setPrevId(busStopInfoList.get(curBusStopOrd - 2).getBusStopId());
            curBusStopInfo.setPrevName(busStopInfoList.get(curBusStopOrd - 2).getNodeName());
            curBusStopInfo.setNextId(busStopInfoList.get(curBusStopOrd).getBusStopId());
            curBusStopInfo.setNextName(busStopInfoList.get(curBusStopOrd).getNodeName());
        }
        return curBusStopInfo;
    }
}
