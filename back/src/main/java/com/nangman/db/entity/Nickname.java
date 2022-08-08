package com.nangman.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 닉네임 모델 정의.
 */
@Entity
@Getter
@Setter
public class Nickname extends BaseEntity {

    @Column(nullable = false)
    private String nickname;

    @OneToMany
    @JoinColumn(name = "nickname")
    private List<User> users = new ArrayList<User>();

    public Nickname() {
    }

    public void addUser(User user) {
        this.users.add(user);
        if (user.getNickname() != this) {
            user.setNickname(this);
        }
    }

}
