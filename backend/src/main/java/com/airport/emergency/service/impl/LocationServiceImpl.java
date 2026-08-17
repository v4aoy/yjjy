package com.airport.emergency.service.impl;

import com.airport.emergency.dto.LocationDTO;
import com.airport.emergency.entity.Location;
import com.airport.emergency.entity.Resource;
import com.airport.emergency.repository.LocationRepository;
import com.airport.emergency.repository.ResourceRepository;
import com.airport.emergency.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 位置跟踪服务实现类
 */
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final ResourceRepository resourceRepository;

    @Override
    public LocationDTO recordLocation(LocationDTO locationDTO) {
        Resource resource = resourceRepository.findById(locationDTO.getResourceId())
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        
        Location location = Location.builder()
                .resource(resource)
                .latitude(locationDTO.getLatitude())
                .longitude(locationDTO.getLongitude())
                .address(locationDTO.getAddress())
                .accuracy(locationDTO.getAccuracy())
                .speed(locationDTO.getSpeed())
                .heading(locationDTO.getHeading())
                .timestamp(LocalDateTime.now())
                .remark(locationDTO.getRemark())
                .build();
        
        Location savedLocation = locationRepository.save(location);
        return convertToDTO(savedLocation);
    }

    @Override
    public LocationDTO getLatestLocation(Long resourceId) {
        List<Location> locations = locationRepository.findByResourceIdOrderByTimestampDesc(resourceId);
        if (locations.isEmpty()) {
            throw new RuntimeException("No location found for resource");
        }
        return convertToDTO(locations.get(0));
    }

    @Override
    public List<LocationDTO> getLocationHistory(Long resourceId, Integer limit) {
        List<Location> locations = locationRepository.findByResourceIdOrderByTimestampDesc(resourceId);
        return locations.stream()
                .limit(limit != null ? limit : 100)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LocationDTO> getLocationsBetweenTime(Long resourceId, LocalDateTime startTime, LocalDateTime endTime) {
        List<Location> locations = locationRepository.findLocationsAfter(resourceId, startTime);
        return locations.stream()
                .filter(l -> l.getTimestamp().isBefore(endTime) || l.getTimestamp().isEqual(endTime))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LocationDTO> getAllCurrentLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private LocationDTO convertToDTO(Location location) {
        return LocationDTO.builder()
                .id(location.getId())
                .resourceId(location.getResource().getId())
                .resourceName(location.getResource().getName())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .address(location.getAddress())
                .accuracy(location.getAccuracy())
                .speed(location.getSpeed())
                .heading(location.getHeading())
                .timestamp(location.getTimestamp())
                .remark(location.getRemark())
                .build();
    }
}
