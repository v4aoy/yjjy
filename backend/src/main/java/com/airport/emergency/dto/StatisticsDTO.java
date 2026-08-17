package com.airport.emergency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统计数据DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsDTO {
    private Long totalEvents;
    private Long resolvedEvents;
    private Long pendingEvents;
    private Long totalResources;
    private Long availableResources;
    private Long inUseResources;
    private Double averageResolutionTime;
    private Long activeUsers;
}
