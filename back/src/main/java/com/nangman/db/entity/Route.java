package com.nangman.db.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 노선 모델 정의.
 */
@Entity
@Getter
@Setter
@ToString
public class Route {

    @Id
    @Column(updatable = false)
    private String code;

    private String no;

    private String startTime;

    private String endTime;

    private String startBusstop;

    private String endBusstop;

    private String type;

    private int citycode;

    @OneToMany(mappedBy = "route")
    private List<Bus> buses = new ArrayList<>();

    public void addBus(Bus bus) {
        this.buses.add(bus);
        if (bus.getRoute() != this) {
            bus.setRoute(this);
        }
    }

    @OneToMany(mappedBy = "route")
    private List<Busstop> busstops = new ArrayList<>();

    public void addBus(Busstop busstop) {
        this.busstops.add(busstop);
        if (busstop.getRoute() != this) {
            busstop.setRoute(this);
        }
    }

}
