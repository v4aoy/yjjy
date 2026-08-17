package com.airport.emergency.service;

import com.airport.emergency.dto.LocationDTO;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 位置追踪服务接口
 */
public interface LocationService {
    /**
     * 记录位置
     */
    LocationDTO recordLocation(LocationDTO locationDTO);

    /**
     * 获取资源的最新位置
     */
    LocationDTO getLatestLocation(Long resourceId);

    /**
     * 获取资源的位置历史
     */
    List<LocationDTO> getLocationHistory(Long resourceId, Integer limit);

    /**
     * 获取资源在时间段内的位置
     */
    List<LocationDTO> getLocationsBetweenTime(Long resourceId, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 获取所有资源的实时位置
     */
    List<LocationDTO> getAllCurrentLocations();
}
