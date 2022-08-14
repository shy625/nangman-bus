package com.nangman.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class BusStopDto {

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Info {
        private Integer nodeNo;     // 정류소 번호
        private Double lat;
        private Double lng;
        private String nodeName;
        private Integer nodeOrd;
        private String nodeId;  // 정류소 id
        private Integer upDown = 0;     // 상하행 구분 [0 : 없음, 1 : 상행, 2 : 하행]
    }

}
