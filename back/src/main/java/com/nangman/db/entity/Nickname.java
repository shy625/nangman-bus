package com.nangman.db.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 닉네임 모델 정의.
 */
@Entity
@Getter
public class Nickname extends BaseEntity {

    @Column(nullable = false)
    private String nickname;

    @OneToMany
    @JoinColumn(name = "nickname")
    private List<User> users = new ArrayList<User>();

    public void addUser(User user) {
        this.users.add(user);
        if (user.getUserNickname() != this) {
            user.setNickname(this);
        }
    }

}
