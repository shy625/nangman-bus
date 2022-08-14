package com.nangman.redis5.service;

import com.nangman.api.dto.ChatDto;
import com.nangman.db.entity.Bus;
import com.nangman.db.entity.BusStop;
import com.nangman.db.entity.Route;
import com.nangman.db.repository.RouteRepository;
import com.nangman.redis5.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{

    private static final double BUS_CHECK_DIST = 20000.0;

    private static final int BUS_INFO_LICENSE_NO = 0;
    private static final int BUS_INFO_ROUTE_ID = 1;
    private static final int BUS_INFO_LAT = 2;
    private static final int BUS_INFO_LNG = 3;
    private static final int BUS_INFO_CREATED_DATE = 4;
    private static final int BUS_INFO_NODE_ORD = 5;
    private static final int BUS_INFO_NODE_ID = 6;
    private static final int BUS_INFO_NNAME = 7;
    private static final int BUS_INFO_BUS_ID = 8;

    private static final int CHAT_INFO_USER_ID = 0;
    private static final int CHAT_INFO_CREATED_TIME = 1;
    private static final int CHAT_INFO_CONTENT = 2;

    private static final int USER_INFO_NICKNAME = 0;
    private static final int USER_INFO_BIRTH = 1;
    private static final int USER_INFO_STATE = 2;
    private static final int USER_INFO_BUS_STOP = 3;

    private static final String KEY_ROOM = "_room";
    private static final String KEY_CHAT = "_chat";
    private static final String KEY_LIKE = "_like";
    private static final String SUBKEY_BUS_INFO = "busInfo";
    private static final String SUBKEY_ROUTE_INFO = "routeInfo";
    private static final String SUBKEY_USER_LIST = "userList";
    private static final String SUBKEY_USER_NUM = "userNum";

    private static final String SPLIT_STR = "_";
    private static final int USER_STATE_SPLIT_LIMIT = 3;
    private static final int ICED = 0;
    private static final int NOISY = 1;
    private static final String COUNT = "count";

    private final StringRedisTemplate redisTemplate;
    private final RouteRepository routeRepository;

    public void test01() {
        redisTemplate.opsForHash().put("key1", "subKey1", "hello");
    }

    @Override
    public void updateBudData(Bus bus) {
        //현규가 버스 entity 업데이트하면 ㄱ
        String keyRoom = bus.getSessionId() + KEY_ROOM;
        StringBuilder createBusInfo = new StringBuilder();
        createBusInfo.append(bus.getLicenseNo())
                .append(SPLIT_STR)
                .append(bus.getRouteNo())
                .append(SPLIT_STR)
                .append(bus.getLat())
                .append(SPLIT_STR)
                .append(bus.getLng())
                .append(SPLIT_STR)
                .append(bus.getCreatedDate())
                .append(SPLIT_STR)
                .append(bus.getNodeOrd())
                .append(SPLIT_STR)
                .append(bus.getNodeId())
                .append(SPLIT_STR)
                .append(bus.getNodeName())
                .append(SPLIT_STR)
                .append(bus.getId());

        redisTemplate.opsForHash().put(keyRoom, SUBKEY_BUS_INFO, createBusInfo.toString());
    }

    @Override
    public void createChattingRoom(Bus bus) {
        String keyRoom = bus.getSessionId() + KEY_ROOM;
        String keyChat = bus.getSessionId() + KEY_CHAT;
        String keyLike = bus.getSessionId() + KEY_LIKE;
        StringBuilder createBusInfo = new StringBuilder();
        StringBuilder createRouteInfo = new StringBuilder();

        createBusInfo.append(bus.getLicenseNo())
                .append(SPLIT_STR)
                .append(bus.getRouteNo())
                .append(SPLIT_STR)
                .append(bus.getLat())
                .append(SPLIT_STR)
                .append(bus.getLng())
                .append(SPLIT_STR)
                .append(bus.getCreatedDate())
                .append(SPLIT_STR)
                .append(bus.getNodeOrd())
                .append(SPLIT_STR)
                .append(bus.getNodeId())
                .append(SPLIT_STR)
                .append(bus.getNodeName())
                .append(SPLIT_STR)
                .append(bus.getId());


        List<BusStop> busStopList = routeRepository.findRouteByCode(bus.getCode()).get().getBusStops();
        for(BusStop str : busStopList) {
            createRouteInfo.append(str.getNodeName()).append(SPLIT_STR);
        }
        createRouteInfo.setLength(createRouteInfo.length() -1);

        redisTemplate.opsForHash().put(keyRoom, SUBKEY_BUS_INFO, createBusInfo.toString());
        redisTemplate.opsForHash().put(keyRoom, SUBKEY_ROUTE_INFO, createRouteInfo.toString());
        redisTemplate.opsForHash().put(keyRoom, SUBKEY_USER_LIST, " ");
        redisTemplate.opsForHash().put(keyRoom, SUBKEY_USER_NUM, "0");

        redisTemplate.opsForHash().put(keyChat, COUNT, "0");
        redisTemplate.opsForHash().put(keyLike, COUNT, "0");
    }

    @Override
    public ChatDto.ChatLog deleteChattingRoom(String sessionId) {
        ChatDto.ChatLog chatLog = new ChatDto.ChatLog();
        String keyRoom = sessionId + KEY_ROOM;
        String keyChat = sessionId + KEY_CHAT;
        String keyLike = sessionId + KEY_LIKE;

        if(!redisTemplate.hasKey(keyRoom)){
            return chatLog;
        }
        String busValue = (String) redisTemplate.opsForHash().get(keyRoom, SUBKEY_BUS_INFO);

        String[] busInfo = busValue.split(SPLIT_STR);

        chatLog.setSessionId(sessionId);
        chatLog.setLicenseNo(busInfo[BUS_INFO_LICENSE_NO]);
        chatLog.setRouteId(busInfo[BUS_INFO_ROUTE_ID]);
        chatLog.setCreatedDate(busInfo[BUS_INFO_CREATED_DATE]);

        List<ChatDto.MsgLog> logs = new ArrayList<>();
        Map<Object, Object> roomChat = redisTemplate.opsForHash().entries(keyChat);
        Map<Object, Object> roomLike = redisTemplate.opsForHash().entries(keyLike);

        for(Object str : roomChat.keySet()) {
            ChatDto.MsgLog temp = new ChatDto.MsgLog();
            if(COUNT.equals((String) str)) continue;
            System.out.println(str);
            temp.setChatId(str.toString());
            temp.setLike(roomLike.get(str).toString());
            String value = roomChat.get(str).toString();
            String[] values = value.split(SPLIT_STR, USER_STATE_SPLIT_LIMIT);
            temp.setUserId(values[CHAT_INFO_USER_ID]);
            temp.setCreatedTime(values[CHAT_INFO_CREATED_TIME]);
            temp.setContent(values[CHAT_INFO_CONTENT]);

            logs.add(temp);
        }

        chatLog.setChatLogs(logs);
        redisTemplate.delete(keyRoom);
        redisTemplate.delete(keyChat);
        redisTemplate.delete(keyLike);

        return chatLog;
    }

    @Override
    public List<ChattingRoomDto> selectRooms(double lat, double lng) {
        // TODO : busId도 같이 넘겨줘야 됨
        List<ChattingRoomDto> list = new ArrayList<>();
        String findAllRoom = "*" + KEY_ROOM;
        Set<String> keys = redisTemplate.keys(findAllRoom);

        for(String str : keys) {
            String busValue = (String) redisTemplate.opsForHash().get(str, SUBKEY_BUS_INFO);

            String[] busInfo = busValue.split(SPLIT_STR);
            double busLat = Double.parseDouble(busInfo[BUS_INFO_LAT]);
            double busLng = Double.parseDouble(busInfo[BUS_INFO_LNG]);

            double dist = distance(lat, lng, busLat, busLng);

            if(dist < BUS_CHECK_DIST) {
                ChattingRoomDto dto = new ChattingRoomDto();
                dto.setDistance((int) dist);
                dto.setInUsers(Integer.parseInt((String) redisTemplate.opsForHash().get(str, SUBKEY_USER_NUM)));
                dto.setSessionId(str.replace(KEY_ROOM, ""));
                dto.setRouteId(busInfo[BUS_INFO_ROUTE_ID]);
                dto.setBusId(Long.parseLong(busInfo[BUS_INFO_BUS_ID]));
                // 시끌벅적 정도
                dto.setType(getType(str.replace(KEY_ROOM, "")));

                list.add(dto);
            }
        }
        return list;
    }

    @Override
    public boolean isAccessibleRoom(double lat, double lng, String sessionId) {
        String key = sessionId + KEY_ROOM;
        String busValue = (String) redisTemplate.opsForHash().get(key, SUBKEY_BUS_INFO);

        String[] busInfo = busValue.split(SPLIT_STR);
        double busLat = Double.parseDouble(busInfo[BUS_INFO_LAT]);
        double busLng = Double.parseDouble(busInfo[BUS_INFO_LNG]);
        double dist = distance(lat, lng, busLat, busLng);
        if(dist < BUS_CHECK_DIST) return true;
        return false;
    }

    @Override
    public List<RandomBusDto> getRandomBus() {
        List<RandomBusDto> list = new ArrayList<>();
        int index = 0;
        List<String> keys = new ArrayList<>();
        for(int i = 0; i < 1000; i++) {
            String randomKey = redisTemplate.randomKey();
            String originKey = "";
            if (randomKey.endsWith(KEY_ROOM))
                originKey = randomKey.replace(KEY_ROOM, "");
            else if (randomKey.endsWith(KEY_CHAT))
                originKey = randomKey.replace(KEY_CHAT, "");
            else if (randomKey.endsWith(KEY_LIKE))
                originKey = randomKey.replace(KEY_LIKE, "");
            else
                continue;

            boolean check = false;
            for(int j = 0; j < index; j++) {
                if(originKey.equals(keys.get(j))){
                    check = true;
                }
            }
            if(check) continue;
            keys.add(originKey);
            index++;
            if(index >= 3) break;
        }
        for(String str : keys) {
            System.out.println(str);
            String keyRoom = str + KEY_ROOM;
            RandomBusDto randomBusDto = new RandomBusDto();
            String value = (String) redisTemplate.opsForHash().get(keyRoom, SUBKEY_BUS_INFO);
            String[] vals = value.split(SPLIT_STR);
            randomBusDto.setRouteNumber(vals[BUS_INFO_ROUTE_ID]);
            randomBusDto.setNodeName(vals[BUS_INFO_NNAME]);
            randomBusDto.setType(getType(str));

            list.add(randomBusDto);
        }

        return list;
    }

    @Override
    public void upLike(String sessionId, String chatId) {
        String key = sessionId + KEY_LIKE;
        redisTemplate.opsForHash().increment(key, chatId, 1);
    }

    @Override
    public void downLike(String sessionId, String chatId) {
        String key = sessionId + KEY_LIKE;
        redisTemplate.opsForHash().increment(key, chatId, -1);
    }

    @Override
    public int getLike(String sessionId, String chatId) {
        String key = sessionId + KEY_LIKE;
        String subKey = chatId;
        String str = (String) redisTemplate.opsForHash().get(key, subKey);
        return Integer.parseInt(str);
    }

    @Override
    public void updateMyEmotion(String sessionId, String userId, int emotion) {
        String key = sessionId + KEY_ROOM;
        String value = (String) redisTemplate.opsForHash().get(key, userId);
        String[] userInfo = value.split(SPLIT_STR);
        userInfo[USER_INFO_STATE] = Integer.toString(emotion);

        String temp = userInfo[USER_INFO_NICKNAME] + SPLIT_STR + userInfo[USER_INFO_BIRTH] + SPLIT_STR + userInfo[USER_INFO_STATE] + SPLIT_STR + userInfo[USER_INFO_BUS_STOP];

        redisTemplate.opsForHash().put(key, userId, temp);

    }

    @Override
    public List<RoomUserDto> roomUserList(String sessionId) {
        String key = sessionId + KEY_ROOM;
        List<RoomUserDto> list = new ArrayList<>();
        //이건 모든 서브키-밸류값 가져오는거
        Map<Object, Object> values = redisTemplate.opsForHash().entries(key);
        // 현재 방에 있는 유저의 목록은 따로 있음
        String userList = (String) redisTemplate.opsForHash().get(key, SUBKEY_USER_LIST);
        assert userList != null;
        String[] users = userList.split(SPLIT_STR);
        for(Object str : values.keySet()) {
//            if(str.equals("busInfo") || str.equals("routeInfo") || str.equals("userList") || str.equals("userNum")) continue;
            for(String userId : users) {
                if(str.equals(userId)) {
                    RoomUserDto dto = new RoomUserDto();
                    String value = (String) redisTemplate.opsForHash().get(key, str);
                    String[] userInfo = value.split(SPLIT_STR);

                    dto.setNickName(userInfo[USER_INFO_NICKNAME]);
                    dto.setBirth(userInfo[USER_INFO_BIRTH]);
                    dto.setEmotion(Integer.parseInt(userInfo[USER_INFO_STATE]));
                    dto.setOutBusStop(userInfo[USER_INFO_NICKNAME]);

                    list.add(dto);
                }
            }
        }

        return list;
    }

    @Override
    public String createChat(String sessionId, String userId, String CreatedTime, String chat) {
        String keyChat = sessionId + KEY_CHAT;
        String keyLike = sessionId + KEY_LIKE;
        int count = Integer.parseInt((String) redisTemplate.opsForHash().get(keyChat, COUNT));
        count++;
        String subKey = Integer.toString(count);
        StringBuilder value = new StringBuilder();
        value.append(userId)
                .append(SPLIT_STR)
                .append(CreatedTime)
                .append(SPLIT_STR)
                .append(chat);
        redisTemplate.opsForHash().put(keyChat, subKey, value.toString());
        redisTemplate.opsForHash().put(keyLike, subKey, "0");
        redisTemplate.opsForHash().put(keyChat, COUNT, subKey);
        redisTemplate.opsForHash().put(keyLike, COUNT, subKey);

        return subKey;
    }

    @Override
    public void joinRoom(String sessionId, String userId, RoomUserDto roomUserDto) {
        String key = sessionId + KEY_ROOM;
        String userList = (String) redisTemplate.opsForHash().get(key, SUBKEY_USER_LIST);
        userList = userList + SPLIT_STR + userId;
        redisTemplate.opsForHash().put(key, SUBKEY_USER_LIST, userList);
        redisTemplate.opsForHash().increment(key, SUBKEY_USER_NUM, 1);
        // userId로 검색해서 SQL에서 닉네임, 생일 가져워서 넣어주고 상태 디폴트 0, 하차정류장 null 해줘야됨
//        Optional<User> user = userRepository.findByIdAndIsDeletedFalse(Long.parseLong(userId));
//        User myUser = user.get();
//        String value = myUser.getNickname() + splitStr + myUser.getUserBirthday() + splitStr + "0" + splitStr + "null";
        // value = UserRepository.getNickName
        StringBuilder value = new StringBuilder();
        value.append(roomUserDto.getNickName())
             .append(SPLIT_STR)
             .append(roomUserDto.getBirth())
             .append(SPLIT_STR)
             .append(Integer.toString(roomUserDto.getEmotion()))
             .append(SPLIT_STR)
             .append(roomUserDto.getOutBusStop());
        redisTemplate.opsForHash().put(key, userId, value.toString());
    }

    @Override
    public void exitRoom(String sessionId, String userId) {
        String key = sessionId + KEY_ROOM;
        String userList = (String) redisTemplate.opsForHash().get(key, SUBKEY_USER_LIST);
//        userList = userList + ":" + userId;
//        redisTemplate.opsForHash().put(key, "userList", userList);
        String[] users = userList.split(SPLIT_STR);
        StringBuilder newUserList = new StringBuilder();
        for(String str : users) {
            if(str.equals(userId)) continue;
            newUserList.append(str).append(SPLIT_STR);
        }
        newUserList.setLength(newUserList.length() - 1);
        redisTemplate.opsForHash().put(key, SUBKEY_USER_LIST, newUserList.toString());

        redisTemplate.opsForHash().increment(key, SUBKEY_USER_NUM, -1);
    }

    @Override
    public void setOutBusStop(String sessionId, String userId, String outBusStop) {
        String key = sessionId + KEY_ROOM;
        String subKey = userId;
        String value = (String) redisTemplate.opsForHash().get(key, subKey);
        String[] userInfo = value.split(SPLIT_STR);
        StringBuilder newUserInfo = new StringBuilder();
        newUserInfo.append(userInfo[USER_INFO_NICKNAME]).append(SPLIT_STR)
                .append(userInfo[USER_INFO_BIRTH]).append(SPLIT_STR)
                .append(userInfo[USER_INFO_STATE]).append(SPLIT_STR)
                .append(outBusStop);
        redisTemplate.opsForHash().put(key, subKey, newUserInfo.toString());
    }

    @Override
    public List<String> getSessionList() {
        Set<String> redisKeys = redisTemplate.keys("*_chat");
        List<String> keysList = new ArrayList<>();
        Iterator<String> iter = redisKeys.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            keysList.add(key);
        }
        return keysList;
    }


    // 두 좌표 사이의 거리를 구하는 함수
    // dsitance(첫번쨰 좌표의 위도, 첫번째 좌표의 경도, 두번째 좌표의 위도, 두번째 좌표의 경도)
    private double distance(double lat1, double lon1, double lat2, double lon2){
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;

        return dist; //단위 meter
    }
    //10진수를 radian(라디안)으로 변환
    private double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    private double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }

    //타입 반환해주는 함수
    private int getType(String sessionId) {
        String keyChat = sessionId + KEY_CHAT;
        int minute = LocalDateTime.now().getMinute();
        List<Object> list = redisTemplate.opsForHash().values(keyChat);
        int count = 0;
        for(Object obj : list) {
            String str = (String) obj;
            String[] strs = str.split(SPLIT_STR);
            if(strs.length < CHAT_INFO_CONTENT) continue;
            LocalDateTime ldt = LocalDateTime.parse(strs[CHAT_INFO_CREATED_TIME]);
            int temp = ldt.getMinute();
            if(minute >= temp && minute - 5 <= temp) {
                count++;
            }
        }
        if(count > 10) return NOISY;
        return ICED;
    }
}
