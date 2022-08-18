package com.nangman.api.dto;

import com.nangman.db.entity.Bus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

public class BusDto {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class Info {

        private long id;
        private String licenseNo;
        private double lat;
        private double lng;
        private String nodeId;
        private String nodeName;
        private int nodeOrd;
        private String sessionId;
        private String code;
        private String routeNo;
        private List<Long> boardIds = new ArrayList<>();
        private List<Long> roomIds = new ArrayList<>();

        public Info(Bus bus) {
            this.id = bus.getId();
            this.licenseNo = bus.getLicenseNo();
            this.lat = bus.getLat();
            this.lng = bus.getLng();
            this.nodeId = bus.getNodeId();
            this.nodeName = bus.getNodeName();
            this.nodeOrd = bus.getNodeOrd();
            this.sessionId = bus.getSessionId();
            this.code = bus.getCode();
            this.routeNo = bus.getRouteNo();
            for (int i = 0; i < bus.getBoards().size(); i++) boardIds.add(bus.getBoards().get(i).getId());
            for (int i = 0; i < bus.getRooms().size(); i++) roomIds.add(bus.getRooms().get(i).getId());
        }
    }
}
