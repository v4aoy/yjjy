package com.airport.emergency.service;

import com.airport.emergency.dto.EmergencyEventDTO;
import com.airport.emergency.entity.EmergencyEvent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * 应急事件服务接口
 */
public interface EmergencyEventService {
    /**
     * 创建应急事件
     */
    EmergencyEventDTO createEvent(EmergencyEventDTO eventDTO, Long reporterId);

    /**
     * 获取事件详情
     */
    Optional<EmergencyEventDTO> getEventById(Long id);

    /**
     * 获取所有事件
     */
    List<EmergencyEventDTO> getAllEvents();

    /**
     * 根据状态获取事件
     */
    List<EmergencyEventDTO> getEventsByStatus(EmergencyEvent.EventStatus status);

    /**
     * 根据优先级获取事件
     */
    List<EmergencyEventDTO> getEventsByPriority(EmergencyEvent.EventPriority priority);

    /**
     * 根据类型获取事件
     */
    List<EmergencyEventDTO> getEventsByType(EmergencyEvent.EventType type);

    /**
     * 根据调度员获取事件
     */
    List<EmergencyEventDTO> getEventsByDispatcher(Long dispatcherId);

    /**
     * 获取特定时间段的事件
     */
    List<EmergencyEventDTO> getEventsBetweenTime(LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 分配调度员
     */
    EmergencyEventDTO assignDispatcher(Long eventId, Long dispatcherId);

    /**
     * 更新事件状态
     */
    EmergencyEventDTO updateEventStatus(Long eventId, EmergencyEvent.EventStatus status);

    /**
     * 解决事件
     */
    EmergencyEventDTO resolveEvent(Long eventId, String remark);

    /**
     * 更新事件信息
     */
    EmergencyEventDTO updateEvent(Long eventId, EmergencyEventDTO eventDTO);

    /**
     * 删除事件
     */
    void deleteEvent(Long eventId);
}
