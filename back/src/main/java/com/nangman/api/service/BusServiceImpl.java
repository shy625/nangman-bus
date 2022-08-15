package com.nangman.api.service;

import com.nangman.api.dto.RoomDto;
import com.nangman.common.constants.ErrorCode;
import com.nangman.common.exception.CustomException;
import com.nangman.db.entity.Bus;
import com.nangman.db.entity.Route;
import com.nangman.db.repository.BusRepository;
import com.nangman.db.repository.RouteRepository;
import com.nangman.redis5.service.RedisService;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BusServiceImpl implements BusService{

    private final String serviceKey = "oSS%2FSs8eCD52vvjHQ3Rwri%2B3dYUrHEZNgFYt32rz9Yqhw8MKLRmWRNX0GaX0x%2F1xXK5F8Fnzzu4aBUotzOs%2BRw%3D%3D";
    private final int pageNo = 1;
    private final int numOfRows = 30;
    private final String dataType = "json";

    private final RoomService roomService;
    private final RouteRepository routeRepository;

    private final BusRepository busRepository;

    private final RedisService redisService;

    @Override
    @Transactional
    public void followBuses() {
        List<Route> routeList = routeRepository.findAll();

        for (Route route : routeList){
            String code = route.getCode();
            int cityCode = route.getCityCode();
            String BASE_URL = "http://apis.data.go.kr/1613000/BusLcInfoInqireService/getRouteAcctoBusLcList?" +
                    "serviceKey=" + serviceKey +
                    "&pageNo=" + pageNo +
                    "&numOfRows=" + numOfRows +
                    "&_type=" + dataType +
                    "&cityCode=" + cityCode +
                    "&routeId=" + code;
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
                if (response.get("body") == null) return;
                JSONObject body = (JSONObject)response.get("body");
                if (body.get("items") == null) return;
                JSONObject items = (JSONObject)body.get("items");
                if (body.get("item") == null) return;
                JSONArray jsonBusList = (JSONArray)items.get("item");

                for (int i = 0; i < jsonBusList.size(); i++){
                    JSONObject item = (JSONObject) jsonBusList.get(i);
                    if (item.get("vehicleno") == null) throw new CustomException(ErrorCode.BUS_NOT_FOUND);
                    String licenseNo = item.get("vehicleno") + "";
                    Bus bus = null;
                    if (!busRepository.findBusByLicenseNo(licenseNo).isPresent()){
                        bus = new Bus();
                        bus.setLicenseNo(licenseNo);
                        bus.setCode(route.getCode());
                        bus.setRouteNo(route.getRouteNo());
                    }
                    else bus = busRepository.findBusByLicenseNo(licenseNo).get();

                    if (item.get("gpslong") != null) bus.setLng(Double.parseDouble((item.get("gpslong") + "")));
                    if (item.get("gpslati") != null) bus.setLat(Double.parseDouble((item.get("gpslati") + "")));
                    if (item.get("nodeid") != null) bus.setNodeId(item.get("nodeid") + "");
                    if (item.get("nodenm") != null) bus.setNodeName(item.get("nodenm") + "");
                    if (item.get("nodeord") != null) bus.setNodeOrd(Integer.parseInt(item.get("nodeord") + ""));

                    if (bus.getSessionId() == null){
                        String sessionId = "session_";
                        LocalDateTime time = LocalDateTime.now();
                        ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
                        long epoch = time.atZone(zoneId).toEpochSecond();
                        sessionId += Long.toString(epoch);
                        sessionId += "_";
                        sessionId += Integer.toString(i);
                        bus.setSessionId(sessionId);
                        busRepository.save(bus);
                        roomService.createRoom(new RoomDto.CreateRequest(bus));
                        redisService.createChattingRoom(bus);
                    }
                    else redisService.updateBudData(bus);
                    busRepository.save(bus);
                }
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
