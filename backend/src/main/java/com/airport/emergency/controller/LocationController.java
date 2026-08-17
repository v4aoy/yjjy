package com.airport.emergency.controller;

import com.airport.emergency.dto.LocationDTO;
import com.airport.emergency.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 位置跟踪控制器
 */
@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationDTO> recordLocation(@RequestBody LocationDTO locationDTO) {
        return ResponseEntity.ok(locationService.recordLocation(locationDTO));
    }

    @GetMapping("/latest/{resourceId}")
    public ResponseEntity<LocationDTO> getLatestLocation(@PathVariable Long resourceId) {
        return ResponseEntity.ok(locationService.getLatestLocation(resourceId));
    }

    @GetMapping("/history/{resourceId}")
    public ResponseEntity<List<LocationDTO>> getLocationHistory(
            @PathVariable Long resourceId,
            @RequestParam(required = false) Integer limit) {
        return ResponseEntity.ok(locationService.getLocationHistory(resourceId, limit));
    }

    @GetMapping("/time-range")
    public ResponseEntity<List<LocationDTO>> getLocationsBetweenTime(
            @RequestParam Long resourceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return ResponseEntity.ok(locationService.getLocationsBetweenTime(resourceId, startTime, endTime));
    }

    @GetMapping("/all")
    public ResponseEntity<List<LocationDTO>> getAllCurrentLocations() {
        return ResponseEntity.ok(locationService.getAllCurrentLocations());
    }
}
