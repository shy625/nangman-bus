package com.nangman.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 노선 모델 정의.
 */
@Entity
@Getter
@Setter
public class Route extends BaseEntity{

    //라우트id
    private String code;
    //노선번호(버스번호)
    private String routeNo;

    private String startTime;

    private String endTime;

    private String startBusStop;

    private String endBusStop;

    private String routeType;

    private int cityCode;

    @OneToMany(mappedBy = "route")
    private List<BusStop> busStops = new ArrayList<>();

    public void addBusStop(BusStop busstop) {
        this.busStops.add(busstop);
        if (busstop.getRoute() != this) {
            busstop.setRoute(this);
        }
    }

}
