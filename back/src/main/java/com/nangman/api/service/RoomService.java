
package com.nangman.api.service;

import com.nangman.api.dto.RoomDto;
import com.nangman.db.entity.Room;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface RoomService {

    @Transactional
    Room createRoom(RoomDto.CreateRequest createRequest);

    @Transactional
    Room updateRoom(Room room);

}