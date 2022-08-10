package com.nangman.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * 정류장 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
public class Busstop extends BaseEntity {


    //정류소 번호
    private int no;
    private double lat;
    private double lng;
    private String name;
    private int ord;
    //정류소 id
    private String nodeid;
    //노선 코드
    private String code;
    //상하행 구분 [0 : 없음, 1 : 상행, 2 : 하행]
    private int updown = 0;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    public void setRoute(Route route) {
        this.route = route;

        if (!route.getBusstops().contains(this)) {
            route.getBusstops().add(this);
        }
    }
}
