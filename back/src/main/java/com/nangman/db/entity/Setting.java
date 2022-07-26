package com.nangman.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 승객별 설정 모델 정의.
 */
@Entity
@Getter
@Setter
public class Setting extends BaseEntity {

    private boolean whisperMode;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
