package com.nangman.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 차량 모델 정의.
 */
@Entity
@Getter
@Setter
public class BusLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id = null;
    //licenseNo
    private String license;

    private double lat;

    private double lng;

    //정류장 id
    private String nid;
    //몇번째 정류장
    private String ord;
    //busNumber
    private String no;
}
