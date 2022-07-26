package com.nangman.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 정류장 모델 정의.
 */
@Entity
@Getter
@Setter
public class Busstop extends BaseEntity {

    private String no;

    private String name;

    private String sequence;

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
