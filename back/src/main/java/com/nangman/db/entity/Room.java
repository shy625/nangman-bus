package com.nangman.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 닉네임 모델 정의.
 */
@Entity(name = "room")
@Getter
@Setter
public class Room extends BaseEntity {

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime terminatedDate;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    public void setBus(Bus bus) {
        this.bus = bus;

        if (!bus.getRooms().contains(this)) {
            bus.getRooms().add(this);
        }
    }

    @OneToMany(mappedBy = "room")
    private List<Chat> chats = new ArrayList<>();

    public void addChat(Chat chat) {
        this.chats.add(chat);

        if (chat.getRoom() != this) {
            chat.setRoom(this);
        }
    }


    @OneToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @OneToMany(mappedBy = "room")
    private List<ChatInOutRecord> chatInOutRecords = new ArrayList<>();

    public void addChatInOutRecord(ChatInOutRecord chatInOutRecord) {
        this.chatInOutRecords.add(chatInOutRecord);

        if (chatInOutRecord.getRoom() != this) {
            chatInOutRecord.setRoom(this);
        }
    }
}
