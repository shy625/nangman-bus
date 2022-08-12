package com.nangman.common.util;

import java.time.LocalDateTime;

public class TimeCalculator {
    //a : outTime
    //b : inTime
    public static int getAccessTime(LocalDateTime aTime, LocalDateTime bTime) {
        int accessTime = 0;
        accessTime += (aTime.getYear() - bTime.getYear()) * 31536000;
        accessTime += (aTime.getDayOfYear() - bTime.getDayOfYear()) * 86400;
        accessTime += (aTime.getHour() - bTime.getHour()) * 3600;
        accessTime += (aTime.getMinute() - bTime.getMinute()) * 60;
        accessTime += (aTime.getSecond() - bTime.getSecond());
        return accessTime;
    }
}
