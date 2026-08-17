package com.airport.emergency.repository;

import com.airport.emergency.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 位置跟踪Repository
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    
    List<Location> findByResourceIdOrderByTimestampDesc(Long resourceId);
    
    @Query("SELECT l FROM Location l WHERE l.resource.id = :resourceId AND l.timestamp >= :startTime ORDER BY l.timestamp DESC")
    List<Location> findLocationsAfter(@Param("resourceId") Long resourceId, @Param("startTime") LocalDateTime startTime);
    
    @Query("SELECT l FROM Location l WHERE l.timestamp BETWEEN :startTime AND :endTime ORDER BY l.timestamp DESC")
    List<Location> findLocationsBetweenTime(@Param("startTime") LocalDateTime startTime, 
                                            @Param("endTime") LocalDateTime endTime);
}
