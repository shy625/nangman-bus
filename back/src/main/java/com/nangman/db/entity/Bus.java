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
public class Bus extends BaseEntity {

    private String licenseNo;

    private float lat;

    private float lng;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    public void setRoute(Route route) {
        this.route = route;

        if (!route.getBuses().contains(this)) {
            route.getBuses().add(this);
        }
    }
    @OneToMany(mappedBy = "bus")
    private List<Board> boards = new ArrayList<>();

    public void addBoard(Board board) {
        this.boards.add(board);
        if (board.getBus() != this) {
            board.setBus(this);
        }
    }

    @OneToMany(mappedBy = "bus")
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(Room room) {
        this.rooms.add(room);
        if (room.getBus() != this) {
            room.setBus(this);
        }
    }

}
