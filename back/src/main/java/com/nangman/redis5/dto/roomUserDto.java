package com.nangman.redis5.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class roomUserDto {
    String nickName;
    boolean isBirth;
    int emotion;
    String outBusStop;
}
