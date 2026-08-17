package com.airport.emergency.util;

/**
 * 位置工具类
 */
public class LocationUtil {
    
    // 地球半径（公里）
    private static final double EARTH_RADIUS_KM = 6371.0;
    
    /**
     * 计算两个地理位置之间的距离（使用Haversine公式）
     * @param lat1 第一个点的纬度
     * @param lon1 第一个点的经度
     * @param lat2 第二个点的纬度
     * @param lon2 第二个点的经度
     * @return 距离（公里）
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        
        return EARTH_RADIUS_KM * c;
    }
    
    /**
     * 计算两个地理位置之间的距离（返回米）
     */
    public static double calculateDistanceInMeters(double lat1, double lon1, double lat2, double lon2) {
        return calculateDistance(lat1, lon1, lat2, lon2) * 1000;
    }
    
    /**
     * 检查两个位置是���在指定距离内
     */
    public static boolean isWithinDistance(double lat1, double lon1, double lat2, double lon2, double distanceKm) {
        return calculateDistance(lat1, lon1, lat2, lon2) <= distanceKm;
    }
    
    /**
     * 验证纬度是否有效
     */
    public static boolean isValidLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }
    
    /**
     * 验证经度是否有效
     */
    public static boolean isValidLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }
    
    /**
     * 验证地理位置坐标是否有效
     */
    public static boolean isValidLocation(double latitude, double longitude) {
        return isValidLatitude(latitude) && isValidLongitude(longitude);
    }
}
