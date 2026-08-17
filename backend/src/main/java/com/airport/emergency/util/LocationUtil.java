package com.airport.emergency.util;

import org.springframework.stereotype.Component;

/**
 * 位置计算工具类
 */
@Component
public class LocationUtil {
    private static final double EARTH_RADIUS = 6371; // 地球半径（千米）

    /**
     * 计算两个经纬度之间的距离（浮圆公式）
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));

        return EARTH_RADIUS * c; // 距离（千米）
    }

    /**
     * 检查是否位于半径内
     */
    public static boolean isWithinRadius(double lat1, double lon1, double lat2, double lon2, double radius) {
        return calculateDistance(lat1, lon1, lat2, lon2) <= radius;
    }
}
