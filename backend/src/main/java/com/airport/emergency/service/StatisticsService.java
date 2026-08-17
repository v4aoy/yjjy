package com.airport.emergency.service;

import com.airport.emergency.dto.StatisticsDTO;
import java.time.LocalDateTime;

/**
 * 统计分析服务接口
 */
public interface StatisticsService {
    /**
     * 获取系统统计数据
     */
    StatisticsDTO getSystemStatistics();

    /**
     * 获取事件统计
     */
    Long getTotalEvents();

    Long getResolvedEvents();

    Long getPendingEvents();

    /**
     * 获取资源统计
     */
    Long getTotalResources();

    Long getAvailableResources();

    Long getInUseResources();

    /**
     * 获取平均解决时间（分钟）
     */
    Double getAverageResolutionTime();

    /**
     * 获取活跃用户数
     */
    Long getActiveUsers();

    /**
     * 获取特定时间段的事件统计
     */
    StatisticsDTO getStatisticsBetweenTime(LocalDateTime startTime, LocalDateTime endTime);
}
