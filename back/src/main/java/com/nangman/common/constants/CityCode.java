package com.nangman.common.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CityCode {
    Incheon(23),
    Goyang(31100),
    Gwacheon(31110),
    Gwangmyeong(31060),
    Gwangju(31250),
    Guri(31120),
    Gunpo(31160),
    Gimpo(31230),
    Namyangju(31130),
    Dongducheon(31080),
    Bucheon(31050),
    Seongnam(31020),
    Suwon(31010),
    Siheung(31150),
    Ansan(31090),
    Anseong(31220),
    Anyang(31040),
    Yangju(31260),
    Yangpyeong(31380),
    Yeoju(31320),
    Yeoncheon(31350),
    Osan(31140),
    Yongin(31190),
    Ulwang(31170),
    Uijeongbu(31030),
    Icheon(31210),
    Paju(31200),
    Pocheon(31270),
    Pyeongtaek(31070),
    Hanam(31180);

    private final int code;
}
