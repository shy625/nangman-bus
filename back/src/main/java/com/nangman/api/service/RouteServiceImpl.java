package com.nangman.api.service;


import com.nangman.api.dto.RouteDto;
import com.nangman.common.constants.ErrorCode;
import com.nangman.common.exception.CustomException;
import com.nangman.db.entity.BusStop;
import com.nangman.db.entity.Route;
import com.nangman.db.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.routing.RouteInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService{

    private final BusStopService busStopService;
    private final RouteRepository routeRepository;

    private final String serviceKey = "7iPULnyfcY7QCbYSG6l7V1Xt%2BlMtgY1Svqhr%2BpqPNabuvA7obO6MIlH1n5e9BvZeA7oD52cRQTL5ToItgz99cg%3D%3D";
    private final int pageNo = 1;
    private final int numOfRows = 1000;
    private final String dataType = "json";
    private static final Map<String, Integer> cityCode;
    static{
        cityCode = new HashMap<>();
        cityCode.put("인천", 23);
        cityCode.put("고양", 31100);
        cityCode.put("과천", 31110);
        cityCode.put("광명", 31060);
        cityCode.put("광주", 31250);
        cityCode.put("구리", 31120);
        cityCode.put("군포", 31160);
        cityCode.put("김포", 31230);
        cityCode.put("남양주", 31130);
        cityCode.put("동두천", 31080);
        cityCode.put("부천", 31050);
        cityCode.put("성남", 31020);
        cityCode.put("수원", 31010);
        cityCode.put("시흥", 31150);
        cityCode.put("안산", 31090);
        cityCode.put("안성", 31220);
        cityCode.put("안양", 31040);
        cityCode.put("양주", 31260);
        cityCode.put("양평", 31380);
        cityCode.put("여주", 31320);
        cityCode.put("연천", 31350);
        cityCode.put("오산", 31140);
        cityCode.put("용인", 31190);
        cityCode.put("의왕", 31170);
        cityCode.put("의정부", 31030);
        cityCode.put("이천", 31210);
        cityCode.put("파주", 31200);
        cityCode.put("포천", 31270);
        cityCode.put("평택", 31070);
        cityCode.put("하남", 31180);
    }

    @Override
    @Transactional
    public RouteDto.Info followBus(RouteDto.Request request){
        String BASE_URL = "http://apis.data.go.kr/1613000/BusRouteInfoInqireService/getRouteNoList?" +
                "serviceKey=" + serviceKey +
                "&pageNo=" + pageNo +
                "&numOfRows=" + numOfRows +
                "&_type=" + dataType +
                "&cityCode=" + cityCode.get(request.getCityName()) +
                "&routeNo=" + request.getNo();

        // 파싱한 데이터를 저장할 변수
        String result = "";
        List<BusStop> busStopList = null;
        Route route = new Route();
        RouteDto.Info routeInfo = new RouteDto.Info();

        try {
            URL url = new URL(BASE_URL);

            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject response = (JSONObject)jsonObject.get("response");
            JSONObject body = (JSONObject)response.get("body");
            JSONObject items = (JSONObject)body.get("items");
            JSONArray jsonRouteList = new JSONArray();
            Object checker = items.get("item");
            if (checker instanceof JSONObject) jsonRouteList.add(checker);
            else jsonRouteList = (JSONArray) checker;
            if (jsonRouteList == null || jsonRouteList.size() == 0 ) throw new CustomException(ErrorCode.REPORT_NOT_FOUND);
            for (int i = 0; i < jsonRouteList.size(); i++) {
                JSONObject item = (JSONObject) jsonRouteList.get(i);
                if (!item.get("routeno").toString().equals(request.getNo())) continue;
                if (item.get("routeid") != null) route.setCode((String) item.get("routeid") + "");
                if (item.get("startnodenm") != null) route.setStartBusStop(item.get("startnodenm") + "");
                if (item.get("startvehicletime") != null) route.setStartTime(item.get("startvehicletime") + "");
                if (item.get("endnodenm") != null) route.setEndBusStop(item.get("endnodenm") + "");
                if (item.get("endvehicletime") != null) route.setEndTime(item.get("endvehicletime") + "");
                if (item.get("routetp") != null) route.setRouteType(item.get("routetp") + "");
            }
            if (route.getCode() == null)  throw new CustomException(ErrorCode.REPORT_NOT_FOUND);
            route.setCityCode(cityCode.get(request.getCityName()));
            route.setRouteNo(request.getNo());
            routeRepository.save(route);
            busStopList = busStopService.addBusStop(route);
            route.setBusStops(busStopList);
            route = routeRepository.findRouteById(route.getId()).get();
            routeInfo.setRouteNo(route.getRouteNo());
            routeInfo.setRouteType(route.getRouteType());
            routeInfo.setCode(route.getCode());
            routeInfo.setCityCode(route.getCityCode());
            routeInfo.setStartBusStop(route.getStartBusStop());
            routeInfo.setEndBusStop(route.getEndBusStop());
            routeInfo.setStartTime(route.getStartTime());
            routeInfo.setEndTime(route.getEndTime());
            List<RouteDto.BusstopInfo> busstopInfoList = new ArrayList<>();
            for (BusStop busStop: route.getBusStops()){
                RouteDto.BusstopInfo busstopInfo = new RouteDto.BusstopInfo();
                busstopInfo.setNodeName(busStop.getNodeName());
                busstopInfo.setNodeOrd(busStop.getNodeOrd());
                busstopInfoList.add(busstopInfo);
            }
            routeInfo.setBusstopInfoList(busstopInfoList);

        }catch(Exception e) {
            e.printStackTrace();
        }
        return routeInfo;
    }

    @Override
    @Transactional
    public String unfollowBus(RouteDto.Request request) {

        String BASE_URL = "http://apis.data.go.kr/1613000/BusRouteInfoInqireService/getRouteNoList?" +
                "serviceKey=" + serviceKey +
                "&pageNo=" + pageNo +
                "&numOfRows=" + numOfRows +
                "&_type=" + dataType +
                "&cityCode=" + cityCode.get(request.getCityName()) +
                "&routeNo=" + request.getNo();

        // 파싱한 데이터를 저장할 변수
        String result = "";
        String code = "";

        try {
            URL url = new URL(BASE_URL);

            BufferedReader bf;

            bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

            result = bf.readLine();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(result);
            JSONObject response = (JSONObject)jsonObject.get("response");
            JSONObject body = (JSONObject)response.get("body");
            JSONObject items = (JSONObject)body.get("items");
            JSONArray jsonRouteList = new JSONArray();
            Object checker = items.get("item");
            if (checker instanceof JSONObject) jsonRouteList.add(checker);
            else jsonRouteList = (JSONArray) checker;
            if (jsonRouteList == null || jsonRouteList.size() == 0 ) throw new CustomException(ErrorCode.REPORT_NOT_FOUND);
            for (int i = 0; i < jsonRouteList.size(); i++) {
                JSONObject item = (JSONObject) jsonRouteList.get(i);
                if (!item.get("routeno").toString().equals(request.getNo())) continue;
                code = item.get("routeid") + "";
            }
            if (code.equals(""))  throw new CustomException(ErrorCode.REPORT_NOT_FOUND);
            Route route = routeRepository.findRouteByCode(code).get();
            busStopService.deleteBusStopByRouteId(route.getId());
            routeRepository.deleteRouteById(route.getId());

        }catch(Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }

    @Override
    @Transactional(readOnly = true)
    public RouteDto.Info getRoute(String code) {
        Route route = routeRepository.findRouteByCode(code).get();
        RouteDto.Info routeInfo = new RouteDto.Info();
        routeInfo.setRouteNo(route.getRouteNo());
        routeInfo.setRouteType(route.getRouteType());
        routeInfo.setCode(route.getCode());
        routeInfo.setCityCode(route.getCityCode());
        routeInfo.setStartBusStop(route.getStartBusStop());
        routeInfo.setEndBusStop(route.getEndBusStop());
        routeInfo.setStartTime(route.getStartTime());
        routeInfo.setEndTime(route.getEndTime());
        List<RouteDto.BusstopInfo> busstopInfoList = new ArrayList<>();
        for (BusStop busStop: route.getBusStops()){
            RouteDto.BusstopInfo busstopInfo = new RouteDto.BusstopInfo();
            busstopInfo.setNodeName(busStop.getNodeName());
            busstopInfo.setNodeOrd(busStop.getNodeOrd());
            busstopInfoList.add(busstopInfo);
        }
        routeInfo.setBusstopInfoList(busstopInfoList);
        return routeInfo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RouteDto.Info> getAll(){
        List<Route> routeList = routeRepository.findAll();
        List<RouteDto.Info> routeInfoList = new ArrayList<>();
        for (Route route : routeList){
            RouteDto.Info routeInfo = new RouteDto.Info();
            routeInfo.setRouteNo(route.getRouteNo());
            routeInfo.setRouteType(route.getRouteType());
            routeInfo.setCode(route.getCode());
            routeInfo.setCityCode(route.getCityCode());
            routeInfo.setStartBusStop(route.getStartBusStop());
            routeInfo.setEndBusStop(route.getEndBusStop());
            routeInfo.setStartTime(route.getStartTime());
            routeInfo.setEndTime(route.getEndTime());
            List<RouteDto.BusstopInfo> busstopInfoList = new ArrayList<>();
            for (BusStop busStop: route.getBusStops()){
                RouteDto.BusstopInfo busstopInfo = new RouteDto.BusstopInfo();
                busstopInfo.setNodeName(busStop.getNodeName());
                busstopInfo.setNodeOrd(busStop.getNodeOrd());
                busstopInfoList.add(busstopInfo);
            }
            routeInfo.setBusstopInfoList(busstopInfoList);
            routeInfoList.add(routeInfo);
        }
        return routeInfoList;
    }
}
