package com.nangman.db.repository;

import com.nangman.db.entity.ChatInOutRecord;
import com.nangman.db.entity.Room;
import com.nangman.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatInOutRecordRepository extends JpaRepository<ChatInOutRecord, Long> {
    ChatInOutRecord findChatInOutRecordByUserIdAndsessionId(Long userId, Long sessionId);
}