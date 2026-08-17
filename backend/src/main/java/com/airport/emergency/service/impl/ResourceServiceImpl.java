package com.airport.emergency.service.impl;

import com.airport.emergency.dto.ResourceDTO;
import com.airport.emergency.entity.EmergencyEvent;
import com.airport.emergency.entity.Resource;
import com.airport.emergency.entity.User;
import com.airport.emergency.repository.EmergencyEventRepository;
import com.airport.emergency.repository.ResourceRepository;
import com.airport.emergency.repository.UserRepository;
import com.airport.emergency.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 资源服务实现类
 */
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;
    private final EmergencyEventRepository eventRepository;

    @Override
    public ResourceDTO createResource(ResourceDTO resourceDTO) {
        Resource resource = Resource.builder()
                .name(resourceDTO.getName())
                .type(Resource.ResourceType.valueOf(resourceDTO.getType()))
                .code(resourceDTO.getCode())
                .description(resourceDTO.getDescription())
                .category(Resource.ResourceCategory.valueOf(resourceDTO.getCategory()))
                .location(resourceDTO.getLocation())
                .latitude(resourceDTO.getLatitude())
                .longitude(resourceDTO.getLongitude())
                .capacity(resourceDTO.getCapacity())
                .manufacturer(resourceDTO.getManufacturer())
                .model(resourceDTO.getModel())
                .build();
        
        if (resourceDTO.getOwner() != null && resourceDTO.getOwner().getId() != null) {
            User owner = userRepository.findById(resourceDTO.getOwner().getId())
                    .orElseThrow(() -> new RuntimeException("Owner not found"));
            resource.setOwner(owner);
        }
        
        Resource savedResource = resourceRepository.save(resource);
        return ResourceDTO.fromEntity(savedResource);
    }

    @Override
    public Optional<ResourceDTO> getResourceById(Long id) {
        return resourceRepository.findById(id).map(ResourceDTO::fromEntity);
    }

    @Override
    public Optional<ResourceDTO> getResourceByCode(String code) {
        return resourceRepository.findByCode(code).map(ResourceDTO::fromEntity);
    }

    @Override
    public List<ResourceDTO> getAllResources() {
        return resourceRepository.findAll().stream()
                .map(ResourceDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceDTO> getResourcesByType(Resource.ResourceType type) {
        return resourceRepository.findByType(type).stream()
                .map(ResourceDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceDTO> getResourcesByCategory(Resource.ResourceCategory category) {
        return resourceRepository.findByCategory(category).stream()
                .map(ResourceDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceDTO> getResourcesByOwner(Long ownerId) {
        return resourceRepository.findByOwnerId(ownerId).stream()
                .map(ResourceDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceDTO> getAvailableResources() {
        return resourceRepository.findAvailableResources().stream()
                .map(ResourceDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceDTO> getNearbyResources(Double latitude, Double longitude, Double radius) {
        return resourceRepository.findNearbyResources(latitude, longitude, radius).stream()
                .map(ResourceDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ResourceDTO assignResourceToEvent(Long resourceId, Long eventId) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        EmergencyEvent event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        
        resource.setEvent(event);
        resource.setStatus(Resource.ResourceStatus.IN_USE);
        resource.setAssignedAt(LocalDateTime.now());
        Resource savedResource = resourceRepository.save(resource);
        return ResourceDTO.fromEntity(savedResource);
    }

    @Override
    public ResourceDTO releaseResource(Long resourceId) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        resource.setEvent(null);
        resource.setStatus(Resource.ResourceStatus.AVAILABLE);
        resource.setAssignedAt(null);
        Resource savedResource = resourceRepository.save(resource);
        return ResourceDTO.fromEntity(savedResource);
    }

    @Override
    public ResourceDTO updateResourceStatus(Long resourceId, Resource.ResourceStatus status) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        resource.setStatus(status);
        Resource savedResource = resourceRepository.save(resource);
        return ResourceDTO.fromEntity(savedResource);
    }

    @Override
    public ResourceDTO updateResource(Long resourceId, ResourceDTO resourceDTO) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
        
        if (resourceDTO.getName() != null) {
            resource.setName(resourceDTO.getName());
        }
        if (resourceDTO.getDescription() != null) {
            resource.setDescription(resourceDTO.getDescription());
        }
        if (resourceDTO.getLocation() != null) {
            resource.setLocation(resourceDTO.getLocation());
        }
        if (resourceDTO.getLatitude() != null) {
            resource.setLatitude(resourceDTO.getLatitude());
        }
        if (resourceDTO.getLongitude() != null) {
            resource.setLongitude(resourceDTO.getLongitude());
        }
        if (resourceDTO.getCapacity() != null) {
            resource.setCapacity(resourceDTO.getCapacity());
        }
        
        Resource savedResource = resourceRepository.save(resource);
        return ResourceDTO.fromEntity(savedResource);
    }

    @Override
    public void deleteResource(Long resourceId) {
        resourceRepository.deleteById(resourceId);
    }
}
