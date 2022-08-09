package com.nangman.redis5.service;

import com.nangman.db.entity.Bus;
import com.nangman.db.entity.User;
import com.nangman.db.repository.UserRepository;
import com.nangman.redis5.config.RedisRepositoryConfig;
import com.nangman.redis5.dto.chatLogDto;
import com.nangman.redis5.dto.chattingRoomDto;
import com.nangman.redis5.dto.logDto;
import com.nangman.redis5.dto.roomUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
    public chatLogDto deleteChattingRoom(String sessionId) {
        chatLogDto chatLog = null;
        String roomId = sessionId + "_room";
        String roomId_chat = sessionId + "_chat";
        String roomId_like = sessionId + "_like";

        String busValue = (String) redisTemplate.opsForHash().get(roomId, "busInfo");

        String[] busInfo = busValue.split(":");

        chatLog.setSessionId(sessionId);
        chatLog.setLicenseNo(busInfo[busInfoLicenseNo]);
        chatLog.setRouteId(busInfo[busInfoRouteId]);
        chatLog.setCreatedDate(busInfo[busInfoCreatedDate]);

        List<logDto> logs = null;
        Map<Object, Object> roomChat = redisTemplate.opsForHash().entries(roomId_chat);
        Map<Object, Object> roomLike = redisTemplate.opsForHash().entries(roomId_like);

        for(Object str : roomChat.keySet()) {
            logDto temp = null;

            temp.setChatId(str.toString());
            temp.setLike(roomLike.get(str).toString());
            String value = roomChat.get(str).toString();
            String[] values = value.split(":", 3);
            temp.setUserId(values[chatInfoUserId]);
            temp.setCreatedTime(values[chatInfoCreatedTime]);
            temp.setContent(values[chatInfoContent]);

            logs.add(temp);
        }

        chatLog.setChatLogs(logs);

        return chatLog;
    }

    @Override
    public List<chattingRoomDto> selectRooms(double lat, double lng) {
        List<chattingRoomDto> list = null;
        Set<String> keys = redisTemplate.keys("*_room");

        for(String str : keys) {
            String busValue = (String) redisTemplate.opsForHash().get(str, "busInfo");

            String[] busInfo = busValue.split(":");
            double busLat = Double.parseDouble(busInfo[busInfoLat]);
            double busLng = Double.parseDouble(busInfo[busInfoLng]);

            double dist = distance(lat, lng, busLat, busLng);

            if(dist < busCheckDist) {
                chattingRoomDto dto = null;
                dto.setDistance((int) dist);
                dto.setInUsers((Integer) redisTemplate.opsForHash().get(str, "User_num"));
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
        String key = sessionId + "_room";
        String busValue = (String) redisTemplate.opsForHash().get(key, "busInfo");

        String[] busInfo = busValue.split(":");
        double busLat = Double.parseDouble(busInfo[busInfoLat]);
        double busLng = Double.parseDouble(busInfo[busInfoLng]);

        double dist = distance(lat, lng, busLat, busLng);
        if(dist < busCheckDist) return true;
        return false;
    }

    @Override
    public void upLike(String sessionId, String chatId) {
        String key = sessionId + "_like";
        redisTemplate.opsForHash().increment(key, chatId, 1);
    }

    @Override
    public void downLike(String sessionId, String chatId) {
        String key = sessionId + "_like";
        redisTemplate.opsForHash().increment(key, chatId, -1);
    }

    @Override
    public void updateMyEmotion(String sessionId, String userId, int emotion) {
        String key = sessionId + "_room";
        String value = (String) redisTemplate.opsForHash().get(key, userId);
        String[] userInfo = value.split(":");
        userInfo[userInfoState] = Integer.toString(emotion);

        String temp = userInfo[userInfoNickname] + ":" + userInfo[userInfoBirth] + ":" + userInfo[userInfoState] + ":" + userInfo[userInfoBusstop];

        redisTemplate.opsForHash().put(key, userId, temp);

    }

    @Override
    public List<roomUserDto> roomUserList(String sessionId) {
        String key = sessionId + "_room";
        List<roomUserDto> list = null;
        Map<Object, Object> values = redisTemplate.opsForHash().entries(key);
        for(Object str : values.keySet()) {
            if(str.equals("busInfo") || str.equals("routeInfo") || str.equals("userList") || str.equals("userNum")) continue;
            roomUserDto dto = null;
            String value = (String) redisTemplate.opsForHash().get(key, str);
            String[] userInfo = value.split(":");

            dto.setNickName(userInfo[userInfoNickname]);
            dto.setBirth(userInfo[userInfoBirth]);
            dto.setEmotion(Integer.parseInt(userInfo[userInfoState]));
            dto.setOutBusStop(userInfo[userInfoNickname]);

            list.add(dto);
        }

        return list;
    }

    @Override
    public void createChat(String sessionId, String userId, String chatId, String CreatedTime, String chat) {
        String key = sessionId + "_chat";
        String subKey = chatId;
        String value = userId + ":" + CreatedTime + ":" + chat;
        redisTemplate.opsForHash().put(key, subKey, value);
    }

    @Override
    public void joinRoom(String sessionId, String userId) {
        String key = sessionId + "_room";
        String userList = (String) redisTemplate.opsForHash().get(key, "userList");
        userList = userList + ":" + userId;
        redisTemplate.opsForHash().put(key, "userList", userList);
        redisTemplate.opsForHash().increment(key, "userNum", 1);
        // userId로 검색해서 SQL에서 닉네임, 생일 가져워서 넣어주고 상태 디폴트 0, 하차정류장 null 해줘야됨
        Optional<User> user = userRepository.findByIdAndIsDeletedFalse(Long.parseLong(userId));
        User myUser = user.get();
        String value = myUser.getNickname() + ":" + myUser.getUserBirthday() + ":" + "0" + ":" + "null";
        // value = UserRepository.getNickName
        // redisTemplate.opsForHash().put(key, userId, value);
    }

    @Override
    public void exitRoom(String sessionId, String userId) {
        String key = sessionId + "_room";
        String userList = (String) redisTemplate.opsForHash().get(key, "userList");
//        userList = userList + ":" + userId;
//        redisTemplate.opsForHash().put(key, "userList", userList);
        String[] users = userList.split(":");
        StringBuilder newUserList = new StringBuilder();
        for(String str : users) {
            if(str.equals(userId)) continue;
            newUserList.append(str).append(":");
        }
        newUserList.setLength(newUserList.length() - 1);
        redisTemplate.opsForHash().put(key, "userList", newUserList.toString());
        redisTemplate.opsForHash().increment(key, "userNum", -1);
    }

    @Override
    public void setOutBusStop(String sessionId, String userId, String outBusStop) {
        String key = sessionId + "_room";
        String subKey = userId;
        String value = (String) redisTemplate.opsForHash().get(key, subKey);
        String[] userInfo = value.split(":");
        StringBuilder newUserInfo = new StringBuilder();
        newUserInfo.append(userInfo[userInfoNickname]).append(":")
                .append(userInfo[userInfoBirth]).append(":")
                .append(userInfo[userInfoState]).append(":")
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
