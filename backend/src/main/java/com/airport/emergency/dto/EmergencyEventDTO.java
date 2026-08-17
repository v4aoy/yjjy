package com.airport.emergency.dto;

import com.airport.emergency.entity.EmergencyEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 应急事件DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmergencyEventDTO {
    private Long id;
    private String title;
    private String description;
    private String eventType;
    private String priority;
    private String status;
    private String location;
    private Double latitude;
    private Double longitude;
    private UserDTO reporter;
    private UserDTO dispatcher;
    private List<ResourceDTO> allocatedResources;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime resolvedAt;
    private String remark;

    public static EmergencyEventDTO fromEntity(EmergencyEvent event) {
        return EmergencyEventDTO.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .eventType(event.getEventType().name())
                .priority(event.getPriority().name())
                .status(event.getStatus().name())
                .location(event.getLocation())
                .latitude(event.getLatitude())
                .longitude(event.getLongitude())
                .reporter(event.getReporter() != null ? UserDTO.fromEntity(event.getReporter()) : null)
                .dispatcher(event.getDispatcher() != null ? UserDTO.fromEntity(event.getDispatcher()) : null)
                .createdAt(event.getCreatedAt())
                .updatedAt(event.getUpdatedAt())
                .resolvedAt(event.getResolvedAt())
                .remark(event.getRemark())
                .build();
    }
}
