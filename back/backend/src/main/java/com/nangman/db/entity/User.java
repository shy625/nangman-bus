package com.nangman.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 승객 모델 정의.
 */
@Entity
@Getter
@Setter
public class User extends BaseEntity {

    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;

    private String useremail;

    private String userBirthday;

    private String socialToken;

    @ManyToOne
    @JoinColumn(name = "nickname_id")
    private Nickname userNickname;

    private boolean isDeleted;

    private String deletedDate;

    private boolean isRouletted;

    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    public void addBoard(Board board) {
        this.boards.add(board);
        if (board.getUser() != this) {
            board.setUser(this);
        }
    }

    // 무한루프 빠지지 않도록 체크
    public void setNickname(Nickname nickname) {
        this.userNickname = nickname;

        if (!nickname.getUsers().contains(this)) {
            nickname.getUsers().add(this);
        }
    }

    @OneToOne(mappedBy = "user")
    private Setting setting;

    @OneToMany(mappedBy = "user")
    private List<Chat> chats = new ArrayList<>();

    public void addChatting(Chat chat) {
        this.chats.add(chat);

        if (chat.getUser() != this) {
            chat.setUser(this);
        }
    }

    @OneToMany(mappedBy = "user")
    private List<ChatInOutRecord> chatInOutRecords = new ArrayList<>();

    public void addChatInOutRecord(ChatInOutRecord chatInOutRecord) {
        this.chatInOutRecords.add(chatInOutRecord);

        if (chatInOutRecord.getUser() != this) {
            chatInOutRecord.setUser(this);
        }
    }

}
