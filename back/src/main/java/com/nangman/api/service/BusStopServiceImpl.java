package com.nangman.api.service;

import com.nangman.db.entity.BusStop;
import com.nangman.db.entity.Route;
import com.nangman.db.repository.BusStopRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BusStopServiceImpl implements BusStopService {
    private final String serviceKey = "7iPULnyfcY7QCbYSG6l7V1Xt%2BlMtgY1Svqhr%2BpqPNabuvA7obO6MIlH1n5e9BvZeA7oD52cRQTL5ToItgz99cg%3D%3D";
    private final int pageNo = 1;
    private final int numOfRows = 200;
    private final String dataType = "json";

    private final BusStopRepository busStopRepository;
    @Override
    @Transactional
    public List<BusStop> addBusStop(Route route) {
        String BASE_URL = "http://apis.data.go.kr/1613000/BusRouteInfoInqireService/getRouteAcctoThrghSttnList?" +
                "serviceKey=" + serviceKey +
                "&pageNo=" + pageNo +
                "&numOfRows=" + numOfRows +
                "&_type=" + dataType +
                "&cityCode=" + route.getCityCode() +
                "&routeId=" + route.getCode();
        List<BusStop> busStopList = new ArrayList<>();
        // 파싱한 데이터를 저장할 변수
        String result = "";
        log.info(BASE_URL);

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
            JSONArray jsonBusStopList = (JSONArray)items.get("item");

            for (int i = 0; i < jsonBusStopList.size(); i++){
                BusStop busStop = new BusStop();
                JSONObject item = (JSONObject) jsonBusStopList.get(i);
                if (item.get("gpslong") != null) busStop.setLng(Double.parseDouble((item.get("gpslong") + "")));
                if (item.get("gpslati") != null) busStop.setLat(Double.parseDouble((item.get("gpslati") + "")));
                if (item.get("nodenm") != null) busStop.setNodeName(item.get("nodenm") + "");
                if (item.get("nodeno") != null) busStop.setNodeNo(Integer.parseInt(item.get("nodeno") + ""));
                if (item.get("nodeord") != null) busStop.setNodeOrd(Integer.parseInt(item.get("nodeord") + ""));
                if (item.get("nodeid") != null) busStop.setNodeId(item.get("nodeid") + "");
                if (item.get("updowncd") != null) busStop.setUpDown(Integer.parseInt(item.get("updowncd") + "") + 1);
                busStop.setRoute(route);
                busStopRepository.save(busStop);
            }
            busStopList = busStopRepository.findBusStopByRouteId(route.getId());
        }catch(Exception e) {
            e.printStackTrace();
        }
        return busStopList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusStop> getBusStopByRouteId(long routeId) {
        return busStopRepository.findBusStopByRouteId(routeId);
    }

    @Override
    @Transactional
    public void deleteBusStopByRouteId(long routeId) {
        busStopRepository.deleteBusStopByRouteId(routeId);
    }
}
