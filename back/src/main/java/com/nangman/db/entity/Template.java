package com.nangman.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 메시지 템플릿 모델 정의.
 */
@Entity
@Getter
@Setter
public class Template extends BaseEntity {

    private int type;

    private String content;
}
