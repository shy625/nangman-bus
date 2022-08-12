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
public class BusStop extends BaseEntity {


    //정류소 번호
    private int nodeNo;
    private double lat;
    private double lng;
    private String nodeName;
    private int nodeOrd;
    //정류소 id
    private String nodeId;
    //노선 코드
    //상하행 구분 [0 : 없음, 1 : 상행, 2 : 하행]
    private int upDown = 0;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    public void setRoute(Route route) {
        this.route = route;

        if (!route.getBusStops().contains(this)) {
            route.getBusStops().add(this);
        }
    }
}
