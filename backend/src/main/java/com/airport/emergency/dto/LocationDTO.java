package com.airport.emergency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * 位置跟踪DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDTO {
    private Long id;
    private Long resourceId;
    private String resourceName;
    private Double latitude;
    private Double longitude;
    private String address;
    private Double accuracy;
    private Double speed;
    private Double heading;
    private LocalDateTime timestamp;
    private String remark;
}
