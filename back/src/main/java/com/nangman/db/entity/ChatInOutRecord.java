package com.nangman.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 채팅방 입퇴장 기록 모델 정의.
 */
@Entity(name = "chat_in_out_record")
@Getter
@Setter
public class ChatInOutRecord extends BaseEntity {

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime InTime;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime outTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
        if (!user.getChatInOutRecords().contains(this)) {
            user.getChatInOutRecords().add(this);
        }
    }

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public void setRoom(Room room) {
        this.room = room;

        if (!room.getChatInOutRecords().contains(this)) {
            room.getChatInOutRecords().add(this);
        }
    }
}
