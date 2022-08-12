package com.nangman.db.repository;

import com.nangman.db.entity.Report;
import com.nangman.db.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findRoomByReport(Report report);
    Optional<Room> findRoomBySessionId(String sessionId);
}