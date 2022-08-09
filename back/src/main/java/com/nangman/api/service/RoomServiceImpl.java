package com.nangman.api.service;

import com.nangman.api.dto.RoomDto;
import com.nangman.db.entity.Room;
import com.nangman.db.repository.BusRepository;
import com.nangman.db.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    RoomRepository roomRepository;
    BusRepository busRepository;
    @Transactional
    @Override
    public Room createRoom(RoomDto.CreateRequest createRequest) {
        Room room = new Room();
        room.setSessionId(createRequest.getSessionId());
        room.setBus(busRepository.findBusByLicenseNo(createRequest.getLicenseNo()).get());
        roomRepository.save(room);
        room = roomRepository.findRoomBySessionId(room.getSessionId()).get();
        return room;
    }
    public Room updateRoom(Room room){
        roomRepository.save(room);
        return roomRepository.findRoomBySessionId(room.getSessionId()).get();
    }
}
