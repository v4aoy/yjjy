package com.airport.emergency.controller;

import com.airport.emergency.dto.StatisticsDTO;
import com.airport.emergency.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

/**
 * 统计分析控制器
 */
@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<StatisticsDTO> getSystemStatistics() {
        return ResponseEntity.ok(statisticsService.getSystemStatistics());
    }

    @GetMapping("/events/total")
    public ResponseEntity<Long> getTotalEvents() {
        return ResponseEntity.ok(statisticsService.getTotalEvents());
    }

    @GetMapping("/events/resolved")
    public ResponseEntity<Long> getResolvedEvents() {
        return ResponseEntity.ok(statisticsService.getResolvedEvents());
    }

    @GetMapping("/events/pending")
    public ResponseEntity<Long> getPendingEvents() {
        return ResponseEntity.ok(statisticsService.getPendingEvents());
    }

    @GetMapping("/resources/total")
    public ResponseEntity<Long> getTotalResources() {
        return ResponseEntity.ok(statisticsService.getTotalResources());
    }

    @GetMapping("/resources/available")
    public ResponseEntity<Long> getAvailableResources() {
        return ResponseEntity.ok(statisticsService.getAvailableResources());
    }

    @GetMapping("/resources/in-use")
    public ResponseEntity<Long> getInUseResources() {
        return ResponseEntity.ok(statisticsService.getInUseResources());
    }

    @GetMapping("/resolution-time")
    public ResponseEntity<Double> getAverageResolutionTime() {
        return ResponseEntity.ok(statisticsService.getAverageResolutionTime());
    }

    @GetMapping("/active-users")
    public ResponseEntity<Long> getActiveUsers() {
        return ResponseEntity.ok(statisticsService.getActiveUsers());
    }

    @GetMapping("/time-range")
    public ResponseEntity<StatisticsDTO> getStatisticsBetweenTime(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return ResponseEntity.ok(statisticsService.getStatisticsBetweenTime(startTime, endTime));
    }
}
