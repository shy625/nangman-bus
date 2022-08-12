package com.nangman.api.service;

import com.nangman.api.dto.MainDto;
import com.nangman.db.entity.*;
import com.nangman.db.repository.BoardRepository;
import com.nangman.db.repository.BusRepository;
import com.nangman.db.repository.ChatInOutRecordRepository;
import com.nangman.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class MainServiceImpl implements MainService{

    private final UserRepository userRepository;
    private final BusRepository busRepository;
    private final ChatInOutRecordRepository chatInOutRecordRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional(readOnly = true)
    public MainDto.Info getMainPageData(long userId) {
        User user = userRepository.findByIdAndIsDeletedFalse(userId).get();
        List<UserReport> userReportList = user.getUserReports();
        List<Report> reportList = new ArrayList<>();
        Map<String, Integer> countLicense = new HashMap<String, Integer>();
        MainDto.Info info = new MainDto.Info();
        info.setRecentBus(new MainDto.RecentBus());
        info.setTop3(new ArrayList<>());

        for (UserReport userReport : userReportList){
            String license = userReport.getReport().getRoom().getBus().getLicenseNo();
            if (countLicense.containsKey(license)) countLicense.put(license, countLicense.get(license) + 1);
            else countLicense.put(license, 1);
        }
        List<String> keySet = new ArrayList<>(countLicense.keySet());
        keySet.sort((o1, o2) -> countLicense.get(o2).compareTo(countLicense.get(o1)));
        for (int i = 0; i < 3 && i < keySet.size(); i++) {
            Bus bus = busRepository.findBusByLicenseNo(keySet.get(i)).get();
            info.getTop3().add(bus);
        }
        ChatInOutRecord history = chatInOutRecordRepository.findTop1ChatInOutRecordByUserIdOrderByInTimeDesc(userId).get();
        info.getRecentBus().setBus(history.getRoom().getBus());
        info.getRecentBus().setTakenTime(history.getInTime());

        int countBoard = 0;
        LocalDateTime timeKey = history.getOutTime();
        List<Board> boardList = boardRepository.findBoardByBusIdOrderByCreatedDateDesc(info.getRecentBus().getBus().getId());
        for (int i = 0; i < boardList.size(); i++){
            Board board = boardList.get(i);
            if (timeKey.isBefore(board.getCreatedDate())) break;
            countBoard++;
        }
        info.getRecentBus().setCountBoard(countBoard);
        return info;
    }
}
