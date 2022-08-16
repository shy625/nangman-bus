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

    //버스번호판
    private String licenseNo;

    private double lat;
    private double lng;

    private String nodeId;
    private String nodeName;
    private int nodeOrd;
    private String sessionId;

    //노선코드
    private String code;
    //노선번호
    private String routeNo;
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
