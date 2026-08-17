package com.airport.emergency.repository;

import com.airport.emergency.entity.EmergencyEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 应急事件Repository
 */
@Repository
public interface EmergencyEventRepository extends JpaRepository<EmergencyEvent, Long> {
    
    List<EmergencyEvent> findByStatus(EmergencyEvent.EventStatus status);
    
    List<EmergencyEvent> findByPriority(EmergencyEvent.EventPriority priority);
    
    List<EmergencyEvent> findByEventType(EmergencyEvent.EventType eventType);
    
    List<EmergencyEvent> findByDispatcherId(Long dispatcherId);
    
    List<EmergencyEvent> findByReporterId(Long reporterId);
    
    @Query("SELECT e FROM EmergencyEvent e WHERE e.createdAt BETWEEN :startTime AND :endTime")
    List<EmergencyEvent> findEventsBetweenTime(@Param("startTime") LocalDateTime startTime, 
                                               @Param("endTime") LocalDateTime endTime);
    
    @Query("SELECT COUNT(e) FROM EmergencyEvent e WHERE e.status = 'RESOLVED'")
    Long countResolvedEvents();
    
    @Query("SELECT COUNT(e) FROM EmergencyEvent e WHERE e.status IN ('REPORTED', 'ASSIGNED', 'IN_PROGRESS')")
    Long countPendingEvents();
}
