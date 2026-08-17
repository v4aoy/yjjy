package com.airport.emergency.util;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 时间处理工具类
 */
@Component
public class TimeUtil {
    /**
     * 计算两个时间的差值（分钟）
     */
    public static long calculateMinutesBetween(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.MINUTES.between(start, end);
    }

    /**
     * 计算两个时间的差值（秒）
     */
    public static long calculateSecondsBetween(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.SECONDS.between(start, end);
    }

    /**
     * 计算两个时间的差值（小时）
     */
    public static long calculateHoursBetween(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.HOURS.between(start, end);
    }

    /**
     * 计算两个时间的差值（天）
     */
    public static long calculateDaysBetween(LocalDateTime start, LocalDateTime end) {
        return ChronoUnit.DAYS.between(start, end);
    }
}
