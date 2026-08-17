package com.airport.emergency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 救援资源实体类（人员、车辆、设备）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_resource")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ResourceType type;

    @Column(unique = true, nullable = false)
    private String code;

    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ResourceStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ResourceCategory category;

    private String location;

    private Double latitude;

    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private EmergencyEvent event;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    private Integer capacity;

    private String manufacturer;

    private String model;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = ResourceStatus.AVAILABLE;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    /**
     * 资源类型
     */
    public enum ResourceType {
        PERSONNEL("人员"),
        VEHICLE("车辆"),
        EQUIPMENT("设备");

        private final String description;

        ResourceType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 资源状态
     */
    public enum ResourceStatus {
        AVAILABLE("可用"),
        IN_USE("使用中"),
        MAINTENANCE("维护中"),
        OUT_OF_SERVICE("停用");

        private final String description;

        ResourceStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 资源分类
     */
    public enum ResourceCategory {
        RESCUE_TEAM("救援队"),
        MEDICAL_TEAM("医疗队"),
        FIRE_ENGINE("消防车"),
        AMBULANCE("救护车"),
        FIRST_AID_KIT("医疗包"),
        FIRE_EXTINGUISHER("灭火器"),
        OTHER("其他");

        private final String description;

        ResourceCategory(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
