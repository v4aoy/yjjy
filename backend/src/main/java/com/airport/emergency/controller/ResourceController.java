package com.airport.emergency.controller;

import com.airport.emergency.dto.ResourceDTO;
import com.airport.emergency.entity.Resource;
import com.airport.emergency.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 资源控制器
 */
@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
public class ResourceController {
    private final ResourceService resourceService;

    @PostMapping
    public ResponseEntity<ResourceDTO> createResource(@RequestBody ResourceDTO resourceDTO) {
        return ResponseEntity.ok(resourceService.createResource(resourceDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO> getResourceById(@PathVariable Long id) {
        return resourceService.getResourceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<ResourceDTO> getResourceByCode(@PathVariable String code) {
        return resourceService.getResourceByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ResourceDTO>> getAllResources() {
        return ResponseEntity.ok(resourceService.getAllResources());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ResourceDTO>> getResourcesByType(@PathVariable String type) {
        return ResponseEntity.ok(resourceService.getResourcesByType(Resource.ResourceType.valueOf(type)));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ResourceDTO>> getResourcesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(resourceService.getResourcesByCategory(Resource.ResourceCategory.valueOf(category)));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ResourceDTO>> getResourcesByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(resourceService.getResourcesByOwner(ownerId));
    }

    @GetMapping("/available")
    public ResponseEntity<List<ResourceDTO>> getAvailableResources() {
        return ResponseEntity.ok(resourceService.getAvailableResources());
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<ResourceDTO>> getNearbyResources(
            @RequestParam Double latitude,
            @RequestParam Double longitude,
            @RequestParam Double radius) {
        return ResponseEntity.ok(resourceService.getNearbyResources(latitude, longitude, radius));
    }

    @PostMapping("/{resourceId}/assign-to-event")
    public ResponseEntity<ResourceDTO> assignResourceToEvent(
            @PathVariable Long resourceId,
            @RequestParam Long eventId) {
        return ResponseEntity.ok(resourceService.assignResourceToEvent(resourceId, eventId));
    }

    @PostMapping("/{resourceId}/release")
    public ResponseEntity<ResourceDTO> releaseResource(@PathVariable Long resourceId) {
        return ResponseEntity.ok(resourceService.releaseResource(resourceId));
    }

    @PutMapping("/{resourceId}/status")
    public ResponseEntity<ResourceDTO> updateResourceStatus(
            @PathVariable Long resourceId,
            @RequestParam String status) {
        return ResponseEntity.ok(resourceService.updateResourceStatus(resourceId, Resource.ResourceStatus.valueOf(status)));
    }

    @PutMapping("/{resourceId}")
    public ResponseEntity<ResourceDTO> updateResource(
            @PathVariable Long resourceId,
            @RequestBody ResourceDTO resourceDTO) {
        return ResponseEntity.ok(resourceService.updateResource(resourceId, resourceDTO));
    }

    @DeleteMapping("/{resourceId}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long resourceId) {
        resourceService.deleteResource(resourceId);
        return ResponseEntity.ok().build();
    }
}
