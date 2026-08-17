package com.airport.emergency.repository;

import com.airport.emergency.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * 资源Repository
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    
    Optional<Resource> findByCode(String code);
    
    List<Resource> findByStatus(Resource.ResourceStatus status);
    
    List<Resource> findByType(Resource.ResourceType type);
    
    List<Resource> findByCategory(Resource.ResourceCategory category);
    
    List<Resource> findByOwnerId(Long ownerId);
    
    List<Resource> findByEventId(Long eventId);
    
    @Query("SELECT r FROM Resource r WHERE r.status = com.airport.emergency.entity.Resource.ResourceStatus.AVAILABLE")
    List<Resource> findAvailableResources();
    
    @Query("SELECT COUNT(r) FROM Resource r WHERE r.status = com.airport.emergency.entity.Resource.ResourceStatus.AVAILABLE")
    Long countAvailableResources();
    
    @Query("SELECT r FROM Resource r WHERE " +
           "SQRT(POWER(r.latitude - :latitude, 2) + POWER(r.longitude - :longitude, 2)) < :radius " +
           "AND r.status = com.airport.emergency.entity.Resource.ResourceStatus.AVAILABLE")
    List<Resource> findNearbyResources(@Param("latitude") Double latitude, 
                                       @Param("longitude") Double longitude,
                                       @Param("radius") Double radius);
}
