package com.nangman.common.util;

import java.time.LocalDateTime;

public class TimeCalculator {
    //a : outTime
    //b : inTime
    //a > b
    public static int getAccessTime(LocalDateTime aTime, LocalDateTime bTime) {
        int accessTime = 0;
        accessTime += (aTime.getYear() - bTime.getYear()) * 60 * 60 * 24 * 365;
        accessTime += (aTime.getDayOfYear() - bTime.getDayOfYear()) * 60 * 60 * 24;
        accessTime += (aTime.getHour() - bTime.getHour()) * 60 * 60;
        accessTime += (aTime.getMinute() - bTime.getMinute()) * 60;
        accessTime += (aTime.getSecond() - bTime.getSecond());
        return accessTime;
    }
}
