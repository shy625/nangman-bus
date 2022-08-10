package com.nangman.api.service;

import com.nangman.api.dto.BusstopDto;
import com.nangman.db.entity.Busstop;
import com.nangman.db.repository.BusstopRepository;
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
public class BusstopServiceImpl implements BusstopService{
    private final String serviceKey = "7iPULnyfcY7QCbYSG6l7V1Xt%2BlMtgY1Svqhr%2BpqPNabuvA7obO6MIlH1n5e9BvZeA7oD52cRQTL5ToItgz99cg%3D%3D";
    private final int pageNo = 1;
    private final int numOfRows = 1000;
    private final String dataType = "json";

    private final BusstopRepository busstopRepository;
    @Override
    public List<Busstop> addBusstop(BusstopDto.Request request) {
        String BASE_URL = "http://apis.data.go.kr/1613000/BusRouteInfoInqireService/getRouteAcctoThrghSttnList?" +
                "serviceKey=" + serviceKey +
                "&pageNo=" + pageNo +
                "&numOfRows=" + numOfRows +
                "&_type=" + dataType +
                "&cityCode=" + request.getCityCode() +
                "&routeId=" + request.getCode();
        List<Busstop> busstopList = new ArrayList<>();
        // 파싱한 데이터를 저장할 변수
        String result = "";

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
            JSONArray jsonBusstopList = (JSONArray)items.get("item");

            for (int i = 0; i < jsonBusstopList.size(); i++){
                Busstop busstop = new Busstop();
                JSONObject item = (JSONObject) jsonBusstopList.get(i);
                if (item.get("gpslong") != null) busstop.setLng(Double.parseDouble((item.get("gpslong") + "")));
                if (item.get("gpslati") != null) busstop.setLat(Double.parseDouble((item.get("gpslati") + "")));
                if (item.get("nodenm") != null) busstop.setName(item.get("nodenm") + "");
                if (item.get("nodeno") != null) busstop.setNo(Integer.parseInt(item.get("nodeno") + ""));
                if (item.get("routeid") != null) busstop.setCode(item.get("routeid") + "");
                if (item.get("nodeord") != null) busstop.setOrd(Integer.parseInt(item.get("nodeord") + ""));
                if (item.get("nodeid") != null) busstop.setNodeid(item.get("nodeid") + "");
                if (item.get("updowncd") != null) busstop.setUpdown(Integer.parseInt(item.get("updowncd") + "") + 1);
                busstopRepository.save(busstop);
            }
            busstopList = busstopRepository.findBusstopByCode(request.getCode());
        }catch(Exception e) {
            e.printStackTrace();
        }
        return busstopList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Busstop> getBusstopByCode(String code) {
        return busstopRepository.findBusstopByCode(code);
    }

    @Override
    @Transactional
    public void deleteBusstopByCode(String code) {
        busstopRepository.deleteBusstopByCode(code);
    }
}
