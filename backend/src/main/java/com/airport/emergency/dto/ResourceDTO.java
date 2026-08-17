package com.airport.emergency.dto;

import com.airport.emergency.entity.Resource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 资源DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceDTO {
    private Long id;
    private String name;
    private String type;
    private String code;
    private String description;
    private String status;
    private String category;
    private String location;
    private Double latitude;
    private Double longitude;
    private UserDTO owner;
    private Long eventId;
    private LocalDateTime assignedAt;
    private Integer capacity;
    private String manufacturer;
    private String model;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ResourceDTO fromEntity(Resource resource) {
        return ResourceDTO.builder()
                .id(resource.getId())
                .name(resource.getName())
                .type(resource.getType().name())
                .code(resource.getCode())
                .description(resource.getDescription())
                .status(resource.getStatus().name())
                .category(resource.getCategory().name())
                .location(resource.getLocation())
                .latitude(resource.getLatitude())
                .longitude(resource.getLongitude())
                .owner(resource.getOwner() != null ? UserDTO.fromEntity(resource.getOwner()) : null)
                .eventId(resource.getEvent() != null ? resource.getEvent().getId() : null)
                .assignedAt(resource.getAssignedAt())
                .capacity(resource.getCapacity())
                .manufacturer(resource.getManufacturer())
                .model(resource.getModel())
                .createdAt(resource.getCreatedAt())
                .updatedAt(resource.getUpdatedAt())
                .build();
    }
}
