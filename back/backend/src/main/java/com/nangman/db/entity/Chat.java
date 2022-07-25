package com.nangman.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 채팅방 모델 정의.
 */
@Entity
@Getter
@Setter
public class Chat extends BaseEntity {


    private String content;

    private int likes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
        if (!user.getChats().contains(this)) {
            user.getChats().add(this);
        }
    }

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public void setRoom(Room room) {
        this.room = room;

        if (!room.getChats().contains(this)) {
            room.getChats().add(this);
        }
    }
}
