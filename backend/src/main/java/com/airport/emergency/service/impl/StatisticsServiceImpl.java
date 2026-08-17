package com.airport.emergency.service.impl;

import com.airport.emergency.dto.StatisticsDTO;
import com.airport.emergency.repository.EmergencyEventRepository;
import com.airport.emergency.repository.ResourceRepository;
import com.airport.emergency.repository.UserRepository;
import com.airport.emergency.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * 统计分析服务实现类
 */
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {
    private final EmergencyEventRepository eventRepository;
    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;

    @Override
    public StatisticsDTO getSystemStatistics() {
        return StatisticsDTO.builder()
                .totalEvents(getTotalEvents())
                .resolvedEvents(getResolvedEvents())
                .pendingEvents(getPendingEvents())
                .totalResources(getTotalResources())
                .availableResources(getAvailableResources())
                .inUseResources(getInUseResources())
                .averageResolutionTime(getAverageResolutionTime())
                .activeUsers(getActiveUsers())
                .build();
    }

    @Override
    public Long getTotalEvents() {
        return (long) eventRepository.findAll().size();
    }

    @Override
    public Long getResolvedEvents() {
        return eventRepository.countResolvedEvents();
    }

    @Override
    public Long getPendingEvents() {
        return eventRepository.countPendingEvents();
    }

    @Override
    public Long getTotalResources() {
        return (long) resourceRepository.findAll().size();
    }

    @Override
    public Long getAvailableResources() {
        return resourceRepository.countAvailableResources();
    }

    @Override
    public Long getInUseResources() {
        return (long) resourceRepository.findAll().stream()
                .filter(r -> "IN_USE".equals(r.getStatus().name()))
                .count();
    }

    @Override
    public Double getAverageResolutionTime() {
        return eventRepository.findAll().stream()
                .filter(e -> e.getResolvedAt() != null && e.getCreatedAt() != null)
                .mapToLong(e -> java.time.temporal.ChronoUnit.MINUTES.between(e.getCreatedAt(), e.getResolvedAt()))
                .average()
                .orElse(0.0);
    }

    @Override
    public Long getActiveUsers() {
        return (long) userRepository.findByEnabled(true).size();
    }

    @Override
    public StatisticsDTO getStatisticsBetweenTime(LocalDateTime startTime, LocalDateTime endTime) {
        long totalEvents = (long) eventRepository.findEventsBetweenTime(startTime, endTime).size();
        return StatisticsDTO.builder()
                .totalEvents(totalEvents)
                .resolvedEvents(getResolvedEvents())
                .pendingEvents(getPendingEvents())
                .totalResources(getTotalResources())
                .availableResources(getAvailableResources())
                .inUseResources(getInUseResources())
                .averageResolutionTime(getAverageResolutionTime())
                .activeUsers(getActiveUsers())
                .build();
    }
}
