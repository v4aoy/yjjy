package com.airport.emergency.controller;

import com.airport.emergency.dto.EmergencyEventDTO;
import com.airport.emergency.entity.EmergencyEvent;
import com.airport.emergency.service.EmergencyEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 应急事件控制器
 */
@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EmergencyEventController {
    private final EmergencyEventService eventService;

    @PostMapping
    public ResponseEntity<EmergencyEventDTO> createEvent(
            @RequestBody EmergencyEventDTO eventDTO,
            @RequestParam Long reporterId) {
        return ResponseEntity.ok(eventService.createEvent(eventDTO, reporterId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmergencyEventDTO> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmergencyEventDTO>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<EmergencyEventDTO>> getEventsByStatus(@PathVariable String status) {
        return ResponseEntity.ok(eventService.getEventsByStatus(EmergencyEvent.EventStatus.valueOf(status)));
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<EmergencyEventDTO>> getEventsByPriority(@PathVariable String priority) {
        return ResponseEntity.ok(eventService.getEventsByPriority(EmergencyEvent.EventPriority.valueOf(priority)));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<EmergencyEventDTO>> getEventsByType(@PathVariable String type) {
        return ResponseEntity.ok(eventService.getEventsByType(EmergencyEvent.EventType.valueOf(type)));
    }

    @GetMapping("/dispatcher/{dispatcherId}")
    public ResponseEntity<List<EmergencyEventDTO>> getEventsByDispatcher(@PathVariable Long dispatcherId) {
        return ResponseEntity.ok(eventService.getEventsByDispatcher(dispatcherId));
    }

    @GetMapping("/time-range")
    public ResponseEntity<List<EmergencyEventDTO>> getEventsBetweenTime(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return ResponseEntity.ok(eventService.getEventsBetweenTime(startTime, endTime));
    }

    @PostMapping("/{eventId}/assign-dispatcher")
    public ResponseEntity<EmergencyEventDTO> assignDispatcher(
            @PathVariable Long eventId,
            @RequestParam Long dispatcherId) {
        return ResponseEntity.ok(eventService.assignDispatcher(eventId, dispatcherId));
    }

    @PutMapping("/{eventId}/status")
    public ResponseEntity<EmergencyEventDTO> updateEventStatus(
            @PathVariable Long eventId,
            @RequestParam String status) {
        return ResponseEntity.ok(eventService.updateEventStatus(eventId, EmergencyEvent.EventStatus.valueOf(status)));
    }

    @PostMapping("/{eventId}/resolve")
    public ResponseEntity<EmergencyEventDTO> resolveEvent(
            @PathVariable Long eventId,
            @RequestParam String remark) {
        return ResponseEntity.ok(eventService.resolveEvent(eventId, remark));
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EmergencyEventDTO> updateEvent(
            @PathVariable Long eventId,
            @RequestBody EmergencyEventDTO eventDTO) {
        return ResponseEntity.ok(eventService.updateEvent(eventId, eventDTO));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok().build();
    }
}
