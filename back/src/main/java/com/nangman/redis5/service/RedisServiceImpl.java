package com.nangman.redis5.service;

import com.nangman.api.dto.ChatDto;
import com.nangman.db.entity.Bus;
import com.nangman.db.entity.BusStop;
import com.nangman.redis5.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{
    private final StringRedisTemplate redisTemplate;
//    private final UserRepository userRepository;
    private final double BUSCHECKDIST = 20000.0;
    private final int BUSINFOLICENSENO = 0;
    private final int BUSINFOROUTEID = 1;
    private final int BUSINFOLAT = 2;
    private final int BUSINFOLNG = 3;
    private final int BUSINFOCREATEDDATE = 4;
    private final int BUSINFONODEORD = 5;
    private final int BUSINFONODEID = 6;
    private final int BUSINFONNAME = 7;
    private final int BUSINFOBUSID = 8;

    private final int CHATINFOUSERID = 0;
    private final int CHATINFOCREATEDTIME = 1;
    private final int CHATINFOCONTENT = 2;

    private final int USERINFONICKNAME = 0;
    private final int USERINFOBIRTH = 1;
    private final int USERINFOSTATE = 2;
    private final int USERINFOBUSSTOP = 3;
    private final String KEYROOM = "_room";
    private final String KEYCHAT = "_chat";
    private final String KEYLIKE = "_like";
    private final String SUBKEYBUSINFO = "busInfo";
    private final String SUBKEYROUTEINFO = "routeInfo";
    private final String SUBKEYUSERLIST = "userList";
    private final String SUBKEYUSERNUM = "userNum";
    private final String SPLITSTR = "_";
    private final int USERSTATESPLITLIMIT = 3;
    private final int ICED = 0;
    private final int NOISY = 1;
    private final String COUNT = "count";



    public void test01() {
        redisTemplate.opsForHash().put("key1", "subKey1", "hello");
    }

    @Override
    public void updateBudData(Bus bus) {
        //현규가 버스 entity 업데이트하면 ㄱ
        String keyRoom = bus.getSessionId() + KEYROOM;
        StringBuilder createBusInfo = new StringBuilder();
        createBusInfo.append(bus.getLicenseNo())
                .append(SPLITSTR)
                .append(bus.getRoute().getRouteNo())
                .append(SPLITSTR)
                .append(bus.getLat())
                .append(SPLITSTR)
                .append(bus.getLng())
                .append(SPLITSTR)
                .append(bus.getCreatedDate())
                .append(SPLITSTR)
                .append(bus.getNodeOrd())
                .append(SPLITSTR)
                .append(bus.getNodeId())
                .append(SPLITSTR)
                .append(bus.getNodeName())
                .append(SPLITSTR)
                .append(bus.getId());

        redisTemplate.opsForHash().put(keyRoom, SUBKEYBUSINFO, createBusInfo.toString());
    }

    @Override
    public void createChattingRoom(Bus bus) {
        String keyRoom = bus.getSessionId() + KEYROOM;
        String keyChat = bus.getSessionId() + KEYCHAT;
        String keyLike = bus.getSessionId() + KEYLIKE;
        StringBuilder createBusInfo = new StringBuilder();
        StringBuilder createRouteInfo = new StringBuilder();

        createBusInfo.append(bus.getLicenseNo())
                .append(SPLITSTR)
                .append(bus.getRoute().getRouteNo())
                .append(SPLITSTR)
                .append(bus.getLat())
                .append(SPLITSTR)
                .append(bus.getLng())
                .append(SPLITSTR)
                .append(bus.getCreatedDate())
                .append(SPLITSTR)
                .append(bus.getNodeOrd())
                .append(SPLITSTR)
                .append(bus.getNodeId())
                .append(SPLITSTR)
                .append(bus.getNodeName())
                .append(SPLITSTR)
                .append(bus.getId());


        for(BusStop str : bus.getRoute().getBusStops()) {
            createRouteInfo.append(str.getNodeName()).append(SPLITSTR);
        }
        createRouteInfo.setLength(createRouteInfo.length() -1);

        redisTemplate.opsForHash().put(keyRoom, SUBKEYBUSINFO, createBusInfo.toString());
        redisTemplate.opsForHash().put(keyRoom, SUBKEYROUTEINFO, createRouteInfo.toString());
        redisTemplate.opsForHash().put(keyRoom, SUBKEYUSERLIST, " ");
        redisTemplate.opsForHash().put(keyRoom, SUBKEYUSERNUM, "0");

        redisTemplate.opsForHash().put(keyChat, COUNT, "0");
        redisTemplate.opsForHash().put(keyLike, COUNT, "0");
    }

    @Override
    public ChatDto.ChatLog deleteChattingRoom(String sessionId) {
        ChatDto.ChatLog chatLog = new ChatDto.ChatLog();
        String keyRoom = sessionId + KEYROOM;
        String keyChat = sessionId + KEYCHAT;
        String keyLike = sessionId + KEYLIKE;

        String busValue = (String) redisTemplate.opsForHash().get(keyRoom, SUBKEYBUSINFO);

        String[] busInfo = busValue.split(SPLITSTR);

        chatLog.setSessionId(sessionId);
        chatLog.setLicenseNo(busInfo[BUSINFOLICENSENO]);
        chatLog.setRouteId(busInfo[BUSINFOROUTEID]);
        chatLog.setCreatedDate(busInfo[BUSINFOCREATEDDATE]);

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
            String[] values = value.split(SPLITSTR, USERSTATESPLITLIMIT);
            temp.setUserId(values[CHATINFOUSERID]);
            temp.setCreatedTime(values[CHATINFOCREATEDTIME]);
            temp.setContent(values[CHATINFOCONTENT]);

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
        List<ChattingRoomDto> list = new ArrayList<>();
        String findAllRoom = "*" + KEYROOM;
        Set<String> keys = redisTemplate.keys(findAllRoom);

        for(String str : keys) {
            String busValue = (String) redisTemplate.opsForHash().get(str, SUBKEYBUSINFO);

            String[] busInfo = busValue.split(SPLITSTR);
            double busLat = Double.parseDouble(busInfo[BUSINFOLAT]);
            double busLng = Double.parseDouble(busInfo[BUSINFOLNG]);

            double dist = distance(lat, lng, busLat, busLng);

            if(dist < BUSCHECKDIST) {
                ChattingRoomDto dto = new ChattingRoomDto();
                dto.setDistance((int) dist);
                dto.setInUsers(Integer.parseInt((String) redisTemplate.opsForHash().get(str, SUBKEYUSERNUM)));
                dto.setSessionId(str.replace(KEYROOM, ""));
                dto.setRouteId(busInfo[BUSINFOROUTEID]);
                dto.setBusId(Long.parseLong(busInfo[BUSINFOBUSID]));
                // 시끌벅적 정도
                dto.setType(getType(str.replace(KEYROOM, "")));

                list.add(dto);
            }
        }
        return list;
    }

    @Override
    public boolean isAccessibleRoom(double lat, double lng, String sessionId) {
        String key = sessionId + KEYROOM;
        String busValue = (String) redisTemplate.opsForHash().get(key, SUBKEYBUSINFO);

        String[] busInfo = busValue.split(SPLITSTR);
        double busLat = Double.parseDouble(busInfo[BUSINFOLAT]);
        double busLng = Double.parseDouble(busInfo[BUSINFOLNG]);
        double dist = distance(lat, lng, busLat, busLng);
        if(dist < BUSCHECKDIST) return true;
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
            if (randomKey.endsWith(KEYROOM))
                originKey = randomKey.replace(KEYROOM, "");
            else if (randomKey.endsWith(KEYCHAT))
                originKey = randomKey.replace(KEYCHAT, "");
            else if (randomKey.endsWith(KEYLIKE))
                originKey = randomKey.replace(KEYLIKE, "");
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
            String keyRoom = str + KEYROOM;
            RandomBusDto randomBusDto = new RandomBusDto();
            String value = (String) redisTemplate.opsForHash().get(keyRoom, SUBKEYBUSINFO);
            String[] vals = value.split(SPLITSTR);
            randomBusDto.setRouteNumber(vals[BUSINFOROUTEID]);
            randomBusDto.setNodeName(vals[BUSINFONNAME]);
            randomBusDto.setType(getType(str));

            list.add(randomBusDto);
        }

        return list;
    }

    @Override
    public void upLike(String sessionId, String chatId) {
        String key = sessionId + KEYLIKE;
        redisTemplate.opsForHash().increment(key, chatId, 1);
    }

    @Override
    public void downLike(String sessionId, String chatId) {
        String key = sessionId + KEYLIKE;
        redisTemplate.opsForHash().increment(key, chatId, -1);
    }

    @Override
    public int getLike(String sessionId, String chatId) {
        String key = sessionId + KEYLIKE;
        String subKey = chatId;
        String str = (String) redisTemplate.opsForHash().get(key, subKey);
        return Integer.parseInt(str);
    }

    @Override
    public void updateMyEmotion(String sessionId, String userId, int emotion) {
        String key = sessionId + KEYROOM;
        String value = (String) redisTemplate.opsForHash().get(key, userId);
        String[] userInfo = value.split(SPLITSTR);
        userInfo[USERINFOSTATE] = Integer.toString(emotion);

        String temp = userInfo[USERINFONICKNAME] + SPLITSTR + userInfo[USERINFOBIRTH] + SPLITSTR + userInfo[USERINFOSTATE] + SPLITSTR + userInfo[USERINFOBUSSTOP];

        redisTemplate.opsForHash().put(key, userId, temp);

    }

    @Override
    public List<RoomUserDto> roomUserList(String sessionId) {
        String key = sessionId + KEYROOM;
        List<RoomUserDto> list = new ArrayList<>();
        //이건 모든 서브키-밸류값 가져오는거
        Map<Object, Object> values = redisTemplate.opsForHash().entries(key);
        // 현재 방에 있는 유저의 목록은 따로 있음
        String userList = (String) redisTemplate.opsForHash().get(key, SUBKEYUSERLIST);
        assert userList != null;
        String[] users = userList.split(SPLITSTR);
        for(Object str : values.keySet()) {
//            if(str.equals("busInfo") || str.equals("routeInfo") || str.equals("userList") || str.equals("userNum")) continue;
            for(String userId : users) {
                if(str.equals(userId)) {
                    RoomUserDto dto = new RoomUserDto();
                    String value = (String) redisTemplate.opsForHash().get(key, str);
                    String[] userInfo = value.split(SPLITSTR);

                    dto.setNickName(userInfo[USERINFONICKNAME]);
                    dto.setBirth(userInfo[USERINFOBIRTH]);
                    dto.setEmotion(Integer.parseInt(userInfo[USERINFOSTATE]));
                    dto.setOutBusStop(userInfo[USERINFONICKNAME]);

                    list.add(dto);
                }
            }
        }

        return list;
    }

    @Override
    public String createChat(String sessionId, String userId, String CreatedTime, String chat) {
        String keyChat = sessionId + KEYCHAT;
        String keyLike = sessionId + KEYLIKE;
        int count = Integer.parseInt((String) redisTemplate.opsForHash().get(keyChat, COUNT));
        count++;
        String subKey = Integer.toString(count);
        StringBuilder value = new StringBuilder();
        value.append(userId)
                .append(SPLITSTR)
                .append(CreatedTime)
                .append(SPLITSTR)
                .append(chat);
        redisTemplate.opsForHash().put(keyChat, subKey, value.toString());
        redisTemplate.opsForHash().put(keyLike, subKey, "0");
        redisTemplate.opsForHash().put(keyChat, COUNT, subKey);
        redisTemplate.opsForHash().put(keyLike, COUNT, subKey);

        return subKey;
    }

    @Override
    public void joinRoom(String sessionId, String userId, RoomUserDto roomUserDto) {
        String key = sessionId + KEYROOM;
        String userList = (String) redisTemplate.opsForHash().get(key, SUBKEYUSERLIST);
        userList = userList + SPLITSTR + userId;
        redisTemplate.opsForHash().put(key, SUBKEYUSERLIST, userList);
        redisTemplate.opsForHash().increment(key, SUBKEYUSERNUM, 1);
        // userId로 검색해서 SQL에서 닉네임, 생일 가져워서 넣어주고 상태 디폴트 0, 하차정류장 null 해줘야됨
//        Optional<User> user = userRepository.findByIdAndIsDeletedFalse(Long.parseLong(userId));
//        User myUser = user.get();
//        String value = myUser.getNickname() + splitStr + myUser.getUserBirthday() + splitStr + "0" + splitStr + "null";
        // value = UserRepository.getNickName
        StringBuilder value = new StringBuilder();
        value.append(roomUserDto.getNickName())
             .append(SPLITSTR)
             .append(roomUserDto.getBirth())
             .append(SPLITSTR)
             .append(Integer.toString(roomUserDto.getEmotion()))
             .append(SPLITSTR)
             .append(roomUserDto.getOutBusStop());
        redisTemplate.opsForHash().put(key, userId, value.toString());
    }

    @Override
    public void exitRoom(String sessionId, String userId) {
        String key = sessionId + KEYROOM;
        String userList = (String) redisTemplate.opsForHash().get(key, SUBKEYUSERLIST);
//        userList = userList + ":" + userId;
//        redisTemplate.opsForHash().put(key, "userList", userList);
        String[] users = userList.split(SPLITSTR);
        StringBuilder newUserList = new StringBuilder();
        for(String str : users) {
            if(str.equals(userId)) continue;
            newUserList.append(str).append(SPLITSTR);
        }
        newUserList.setLength(newUserList.length() - 1);
        redisTemplate.opsForHash().put(key, SUBKEYUSERLIST, newUserList.toString());

        redisTemplate.opsForHash().increment(key, SUBKEYUSERNUM, -1);
    }

    @Override
    public void setOutBusStop(String sessionId, String userId, String outBusStop) {
        String key = sessionId + KEYROOM;
        String subKey = userId;
        String value = (String) redisTemplate.opsForHash().get(key, subKey);
        String[] userInfo = value.split(SPLITSTR);
        StringBuilder newUserInfo = new StringBuilder();
        newUserInfo.append(userInfo[USERINFONICKNAME]).append(SPLITSTR)
                .append(userInfo[USERINFOBIRTH]).append(SPLITSTR)
                .append(userInfo[USERINFOSTATE]).append(SPLITSTR)
                .append(outBusStop);
        redisTemplate.opsForHash().put(key, subKey, newUserInfo.toString());
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
        String keyChat = sessionId + KEYCHAT;
        int minute = LocalDateTime.now().getMinute();
        List<Object> list = redisTemplate.opsForHash().values(keyChat);
        int count = 0;
        for(Object obj : list) {
            String str = (String) obj;
            String[] strs = str.split(SPLITSTR);
            if(strs.length < CHATINFOCONTENT) continue;
            LocalDateTime ldt = LocalDateTime.parse(strs[CHATINFOCREATEDTIME]);
            int temp = ldt.getMinute();
            if(minute >= temp && minute - 5 <= temp) {
                count++;
            }
        }
        if(count > 10) return NOISY;
        return ICED;
    }
}
