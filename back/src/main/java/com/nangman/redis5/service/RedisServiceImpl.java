package com.nangman.redis5.service;

import com.nangman.db.entity.Bus;
import com.nangman.db.entity.User;
import com.nangman.db.repository.UserRepository;
import com.nangman.redis5.dto.ChatLogDto;
import com.nangman.redis5.dto.ChattingRoomDto;
import com.nangman.redis5.dto.LogDto;
import com.nangman.redis5.dto.RoomUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService{
    private final StringRedisTemplate redisTemplate;
    private final UserRepository userRepository;
    private final double busCheckDist = 1000.0;
    private final int busInfoLicenseNo = 0;
    private final int busInfoRouteId = 1;
    private final int busInfoLat = 2;
    private final int busInfoLng = 3;
    private final int busInfoLastModified = 4;
    private final int busInfoCreatedDate = 5;
    private final int busInfoNodeord = 6;
    private final int busInfoNodeid = 7;
    private final int busInfoNodenm = 8;

    private final int chatInfoUserId = 0;
    private final int chatInfoCreatedTime = 1;
    private final int chatInfoContent = 2;

    private final int userInfoNickname = 0;
    private final int userInfoBirth = 1;
    private final int userInfoState = 2;
    private final int userInfoBusstop = 3;
    private final String key_room = "_room";
    private final String key_chat = "_chat";
    private final String key_like = "_like";
    private final String subKey_busInfo = "busInfo";
    private final String subKey_routeInfo = "routeInfo";
    private final String subKey_userList = "userList";
    private final String subKey_userNum = "userNum";
    private final String splitStr = ":";
    private final int userStateSplitLimit = 3;



    public void test01() {
        redisTemplate.opsForHash().put("key1", "subKey1", "hello");
    }

    @Override
    public void updateBudData(String sessionId, Bus bus) {
        //현규가 버스 entity 업데이트하면 ㄱ
    }

    @Override
    public void createChattingRoom(String sessionId, Bus bus, List<String> busStop) {
        //현규가 버스 entity 업데이트하고, busStopRepository 업데이트하면 ㄱ
    }

    @Override
    public ChatLogDto deleteChattingRoom(String sessionId) {
        ChatLogDto chatLog = new ChatLogDto();
        String keyRoom = sessionId + key_room;
        String keyChat = sessionId + key_chat;
        String keyLike = sessionId + key_like;

        String busValue = (String) redisTemplate.opsForHash().get(keyRoom, subKey_busInfo);

        String[] busInfo = busValue.split(splitStr);

        chatLog.setSessionId(sessionId);
        chatLog.setLicenseNo(busInfo[busInfoLicenseNo]);
        chatLog.setRouteId(busInfo[busInfoRouteId]);
        chatLog.setCreatedDate(busInfo[busInfoCreatedDate]);

        List<LogDto> logs = new ArrayList<>();
        Map<Object, Object> roomChat = redisTemplate.opsForHash().entries(keyChat);
        System.out.println(roomChat.toString());
        Map<Object, Object> roomLike = redisTemplate.opsForHash().entries(keyLike);
        System.out.println(roomLike.toString());

        for(Object str : roomChat.keySet()) {
            LogDto temp = new LogDto();
            System.out.println(str);
            temp.setChatId(str.toString());
            temp.setLike(roomLike.get(str).toString());
            String value = roomChat.get(str).toString();
            String[] values = value.split(splitStr, userStateSplitLimit);
            temp.setUserId(values[chatInfoUserId]);
            temp.setCreatedTime(values[chatInfoCreatedTime]);
            temp.setContent(values[chatInfoContent]);

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
        String findAllRoom = "*" + key_room;
        Set<String> keys = redisTemplate.keys(findAllRoom);

        for(String str : keys) {
            String busValue = (String) redisTemplate.opsForHash().get(str, subKey_busInfo);

            String[] busInfo = busValue.split(splitStr);
            double busLat = Double.parseDouble(busInfo[busInfoLat]);
            double busLng = Double.parseDouble(busInfo[busInfoLng]);

            double dist = distance(lat, lng, busLat, busLng);

            if(dist < busCheckDist) {
                ChattingRoomDto dto = new ChattingRoomDto();
                dto.setDistance((int) dist);
                dto.setInUsers(Integer.parseInt((String) redisTemplate.opsForHash().get(str, subKey_userNum)));
                dto.setSessionId(str);
                dto.setRouteId(busInfo[busInfoRouteId]);
                // 시끌벅적 정도
                dto.setType(0);

                list.add(dto);
            }
        }
        return list;
    }

    @Override
    public boolean isAccessibleRoom(double lat, double lng, String sessionId) {
        String key = sessionId + key_room;
        String busValue = (String) redisTemplate.opsForHash().get(key, subKey_busInfo);

        String[] busInfo = busValue.split(splitStr);
        double busLat = Double.parseDouble(busInfo[busInfoLat]);
        double busLng = Double.parseDouble(busInfo[busInfoLng]);

        double dist = distance(lat, lng, busLat, busLng);
        if(dist < busCheckDist) return true;
        return false;
    }

    @Override
    public void upLike(String sessionId, String chatId) {
        String key = sessionId + key_like;
        redisTemplate.opsForHash().increment(key, chatId, 1);
    }

    @Override
    public void downLike(String sessionId, String chatId) {
        String key = sessionId + key_like;
        redisTemplate.opsForHash().increment(key, chatId, -1);
    }

    @Override
    public int getLike(String sessionId, String chatId) {
        String key = sessionId + key_like;
        String subKey = chatId;
        String str = (String) redisTemplate.opsForHash().get(key, subKey);
        return Integer.parseInt(str);
    }

    @Override
    public void updateMyEmotion(String sessionId, String userId, int emotion) {
        String key = sessionId + key_room;
        String value = (String) redisTemplate.opsForHash().get(key, userId);
        String[] userInfo = value.split(splitStr);
        userInfo[userInfoState] = Integer.toString(emotion);

        String temp = userInfo[userInfoNickname] + splitStr + userInfo[userInfoBirth] + splitStr + userInfo[userInfoState] + splitStr + userInfo[userInfoBusstop];

        redisTemplate.opsForHash().put(key, userId, temp);

    }

    @Override
    public List<RoomUserDto> roomUserList(String sessionId) {
        String key = sessionId + key_room;
        List<RoomUserDto> list = new ArrayList<>();
        //이건 모든 서브키-밸류값 가져오는거
        Map<Object, Object> values = redisTemplate.opsForHash().entries(key);
        // 현재 방에 있는 유저의 목록은 따로 있음
        String userList = (String) redisTemplate.opsForHash().get(key, subKey_userList);
        assert userList != null;
        String[] users = userList.split(splitStr);
        for(Object str : values.keySet()) {
//            if(str.equals("busInfo") || str.equals("routeInfo") || str.equals("userList") || str.equals("userNum")) continue;
            for(String userId : users) {
                if(str.equals(userId)) {
                    RoomUserDto dto = new RoomUserDto();
                    String value = (String) redisTemplate.opsForHash().get(key, str);
                    String[] userInfo = value.split(splitStr);

                    dto.setNickName(userInfo[userInfoNickname]);
                    dto.setBirth(userInfo[userInfoBirth]);
                    dto.setEmotion(Integer.parseInt(userInfo[userInfoState]));
                    dto.setOutBusStop(userInfo[userInfoNickname]);

                    list.add(dto);
                }
            }
        }

        return list;
    }

    @Override
    public void createChat(String sessionId, String userId, String chatId, String CreatedTime, String chat) {
        String key = sessionId + key_chat;
        String subKey = chatId;
        String value = userId + splitStr + CreatedTime + splitStr + chat;
        redisTemplate.opsForHash().put(key, subKey, value);
        redisTemplate.opsForHash().put(key, subKey, "0");
    }

    @Override
    public void joinRoom(String sessionId, String userId, RoomUserDto roomUserDto) {
        String key = sessionId + key_room;
        String userList = (String) redisTemplate.opsForHash().get(key, subKey_userList);
        userList = userList + splitStr + userId;
        redisTemplate.opsForHash().put(key, subKey_userList, userList);
        redisTemplate.opsForHash().increment(key, subKey_userNum, 1);
        // userId로 검색해서 SQL에서 닉네임, 생일 가져워서 넣어주고 상태 디폴트 0, 하차정류장 null 해줘야됨
//        Optional<User> user = userRepository.findByIdAndIsDeletedFalse(Long.parseLong(userId));
//        User myUser = user.get();
//        String value = myUser.getNickname() + splitStr + myUser.getUserBirthday() + splitStr + "0" + splitStr + "null";
        // value = UserRepository.getNickName
        StringBuilder value = new StringBuilder();
        value.append(roomUserDto.getNickName())
             .append(splitStr)
             .append(roomUserDto.getBirth())
             .append(splitStr)
             .append(roomUserDto.getBirth().toString())
             .append(splitStr)
             .append(roomUserDto.getOutBusStop());
        redisTemplate.opsForHash().put(key, userId, value.toString());
    }

    @Override
    public void exitRoom(String sessionId, String userId) {
        String key = sessionId + key_room;
        String userList = (String) redisTemplate.opsForHash().get(key, subKey_userList);
//        userList = userList + ":" + userId;
//        redisTemplate.opsForHash().put(key, "userList", userList);
        String[] users = userList.split(splitStr);
        StringBuilder newUserList = new StringBuilder();
        for(String str : users) {
            if(str.equals(userId)) continue;
            newUserList.append(str).append(splitStr);
        }
        newUserList.setLength(newUserList.length() - 1);
        redisTemplate.opsForHash().put(key, subKey_userList, newUserList.toString());

        redisTemplate.opsForHash().increment(key, subKey_userNum, -1);
    }

    @Override
    public void setOutBusStop(String sessionId, String userId, String outBusStop) {
        String key = sessionId + key_room;
        String subKey = userId;
        String value = (String) redisTemplate.opsForHash().get(key, subKey);
        String[] userInfo = value.split(splitStr);
        StringBuilder newUserInfo = new StringBuilder();
        newUserInfo.append(userInfo[userInfoNickname]).append(splitStr)
                .append(userInfo[userInfoBirth]).append(splitStr)
                .append(userInfo[userInfoState]).append(splitStr)
                .append(outBusStop);
        redisTemplate.opsForHash().put(key, subKey, newUserInfo.toString());
    }


    // 두 좌표 사이의 거리를 구하는 함수
    // dsitance(첫번쨰 좌표의 위도, 첫번째 좌표의 경도, 두번째 좌표의 위도, 두번째 좌표의 경도)
    private static double distance(double lat1, double lon1, double lat2, double lon2){
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60*1.1515*1609.344;

        return dist; //단위 meter
    }
    //10진수를 radian(라디안)으로 변환
    private static double deg2rad(double deg){
        return (deg * Math.PI/180.0);
    }
    //radian(라디안)을 10진수로 변환
    private static double rad2deg(double rad){
        return (rad * 180 / Math.PI);
    }
}
