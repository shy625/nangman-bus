package com.nangman.db.repository;

import com.nangman.db.entity.ChatInOutRecord;
import com.nangman.db.entity.Room;
import com.nangman.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatInOutRecordRepository extends JpaRepository<ChatInOutRecord, Long> {
    List<ChatInOutRecord> findChatInOutRecordByUserIdAndRoomId(long userId, long roomId);
    Optional<ChatInOutRecord> findTop1ChatInOutRecordByUserIdAndRoomIdOrderByInTimeDesc(long userId, long roomId);
    Optional<ChatInOutRecord> findTop1ChatInOutRecordByUserIdOrderByInTimeDesc(long userId);
    List<ChatInOutRecord> findChatInOutRecordByRoomId(long roomId);
}