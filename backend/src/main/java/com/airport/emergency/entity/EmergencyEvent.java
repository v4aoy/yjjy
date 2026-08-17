package com.airport.emergency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 应急事件实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_emergency_event")
public class EmergencyEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventPriority priority;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    private String location;

    private Double latitude;

    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dispatcher_id")
    private User dispatcher;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resource> allocatedResources;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    private String remark;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = EventStatus.REPORTED;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    /**
     * 事件类型枚举
     */
    public enum EventType {
        FIRE("火灾"),
        ACCIDENT("交通事故"),
        MEDICAL("医疗急救"),
        SECURITY("安全事件"),
        OTHER("其他");

        private final String description;

        EventType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * 事件优先级
     */
    public enum EventPriority {
        CRITICAL("紧急", 1),
        HIGH("高", 2),
        MEDIUM("中", 3),
        LOW("低", 4);

        private final String description;
        private final Integer level;

        EventPriority(String description, Integer level) {
            this.description = description;
            this.level = level;
        }

        public String getDescription() {
            return description;
        }

        public Integer getLevel() {
            return level;
        }
    }

    /**
     * 事件状态
     */
    public enum EventStatus {
        REPORTED("已报告"),
        ASSIGNED("已分配"),
        IN_PROGRESS("进行中"),
        RESOLVED("已解决"),
        CLOSED("已关闭"),
        CANCELLED("已取消");

        private final String description;

        EventStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
