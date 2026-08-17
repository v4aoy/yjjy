package com.airport.emergency.util;

/**
 * 常量定义类
 */
public class Constants {
    
    // 角色常量
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_DISPATCHER = "ROLE_DISPATCHER";
    public static final String ROLE_RESPONDER = "ROLE_RESPONDER";
    public static final String ROLE_REPORTER = "ROLE_REPORTER";
    
    // 事件状态常量
    public static final String EVENT_STATUS_REPORTED = "REPORTED";
    public static final String EVENT_STATUS_ASSIGNED = "ASSIGNED";
    public static final String EVENT_STATUS_IN_PROGRESS = "IN_PROGRESS";
    public static final String EVENT_STATUS_RESOLVED = "RESOLVED";
    public static final String EVENT_STATUS_CANCELLED = "CANCELLED";
    
    // 事件类型常量
    public static final String EVENT_TYPE_FIRE = "FIRE";
    public static final String EVENT_TYPE_MEDICAL = "MEDICAL";
    public static final String EVENT_TYPE_SECURITY = "SECURITY";
    public static final String EVENT_TYPE_HAZMAT = "HAZMAT";
    public static final String EVENT_TYPE_OTHER = "OTHER";
    
    // 优先级常量
    public static final String PRIORITY_LOW = "LOW";
    public static final String PRIORITY_MEDIUM = "MEDIUM";
    public static final String PRIORITY_HIGH = "HIGH";
    public static final String PRIORITY_CRITICAL = "CRITICAL";
    
    // 资源状态常量
    public static final String RESOURCE_STATUS_AVAILABLE = "AVAILABLE";
    public static final String RESOURCE_STATUS_IN_USE = "IN_USE";
    public static final String RESOURCE_STATUS_MAINTENANCE = "MAINTENANCE";
    public static final String RESOURCE_STATUS_OUT_OF_SERVICE = "OUT_OF_SERVICE";
    
    // 资源类型常量
    public static final String RESOURCE_TYPE_VEHICLE = "VEHICLE";
    public static final String RESOURCE_TYPE_EQUIPMENT = "EQUIPMENT";
    public static final String RESOURCE_TYPE_PERSONNEL = "PERSONNEL";
    public static final String RESOURCE_TYPE_OTHER = "OTHER";
    
    // 操作日志常量
    public static final String OPERATION_CREATE = "CREATE";
    public static final String OPERATION_UPDATE = "UPDATE";
    public static final String OPERATION_DELETE = "DELETE";
    public static final String OPERATION_ASSIGN = "ASSIGN";
    public static final String OPERATION_RESOLVE = "RESOLVE";
    
    // 默认值
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int MAX_PAGE_SIZE = 100;
    
    // 缓存键前缀
    public static final String CACHE_KEY_USER = "user:";
    public static final String CACHE_KEY_EVENT = "event:";
    public static final String CACHE_KEY_RESOURCE = "resource:";
    public static final String CACHE_KEY_LOCATION = "location:";
    
    // 其他常量
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";
    public static final long LOCATION_UPDATE_INTERVAL_MS = 5000; // 5秒
    public static final double LOCATION_DISTANCE_THRESHOLD_KM = 0.5; // 500米
}
