package com.nangman.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 시도코드 모델 정의.
 */
@Entity
@Getter
@Setter
public class City extends BaseEntity {

    private String code;

    private String name;

    @OneToMany(mappedBy = "city")
    private List<Route> routes = new ArrayList<>();

    public void addRoute(Route route) {
        this.routes.add(route);

        if (route.getCity() != this) {
            route.setCity(this);
        }
    }


}
