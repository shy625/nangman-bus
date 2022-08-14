package com.nangman.redis5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomUserDto {

    long id;
    String nickName;
    String birth;
    int emotion;
    String outBusStop;
}
