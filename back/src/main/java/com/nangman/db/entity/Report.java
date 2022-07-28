package com.nangman.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 낭만보고서 모델 정의.
 */
@Entity
@Getter
@Setter
public class Report extends BaseEntity {

    private String content;
    private int averageTime;
    private Long totalChatCount;
    private int totalUserCount;
    @OneToOne(mappedBy = "report")
    private Room room;

    @OneToMany(mappedBy = "report")
    private List<UserReport> users = new ArrayList<>();
}
