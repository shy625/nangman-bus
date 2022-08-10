package com.nangman.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 방명록 모델 정의.
 */
@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Board extends BaseEntity{

    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime deletedDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;

        if (!user.getBoards().contains(this)) {
            user.getBoards().add(this);
        }
    }

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    public void setBus(Bus bus) {
        this.bus = bus;

        if (!bus.getBoards().contains(this)) {
            bus.getBoards().add(this);
        }
    }

}
