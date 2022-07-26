package com.nangman.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 낭만보고서 모델 정의.
 */
@Entity
@Getter
@Setter
public class Report extends BaseEntity {

    private String content;

    @OneToOne(mappedBy = "report")
    private Room room;

}
