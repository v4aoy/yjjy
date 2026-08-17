package com.airport.emergency.service.impl;

import com.airport.emergency.dto.EmergencyEventDTO;
import com.airport.emergency.entity.EmergencyEvent;
import com.airport.emergency.entity.User;
import com.airport.emergency.repository.EmergencyEventRepository;
import com.airport.emergency.repository.UserRepository;
import com.airport.emergency.service.EmergencyEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 应急事件服务实现类
 */
@Service
@RequiredArgsConstructor
public class EmergencyEventServiceImpl implements EmergencyEventService {
    private final EmergencyEventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public EmergencyEventDTO createEvent(EmergencyEventDTO eventDTO, Long reporterId) {
        User reporter = userRepository.findById(reporterId)
                .orElseThrow(() -> new RuntimeException("Reporter not found"));
        
        EmergencyEvent event = EmergencyEvent.builder()
                .title(eventDTO.getTitle())
                .description(eventDTO.getDescription())
                .eventType(EmergencyEvent.EventType.valueOf(eventDTO.getEventType()))
                .priority(EmergencyEvent.EventPriority.valueOf(eventDTO.getPriority()))
                .location(eventDTO.getLocation())
                .latitude(eventDTO.getLatitude())
                .longitude(eventDTO.getLongitude())
                .reporter(reporter)
                .build();
        
        EmergencyEvent savedEvent = eventRepository.save(event);
        return EmergencyEventDTO.fromEntity(savedEvent);
    }

    @Override
    public Optional<EmergencyEventDTO> getEventById(Long id) {
        return eventRepository.findById(id).map(EmergencyEventDTO::fromEntity);
    }

    @Override
    public List<EmergencyEventDTO> getAllEvents() {
        return eventRepository.findAll().stream()
                .map(EmergencyEventDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmergencyEventDTO> getEventsByStatus(EmergencyEvent.EventStatus status) {
        return eventRepository.findByStatus(status).stream()
                .map(EmergencyEventDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmergencyEventDTO> getEventsByPriority(EmergencyEvent.EventPriority priority) {
        return eventRepository.findByPriority(priority).stream()
                .map(EmergencyEventDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmergencyEventDTO> getEventsByType(EmergencyEvent.EventType type) {
        return eventRepository.findByEventType(type).stream()
                .map(EmergencyEventDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmergencyEventDTO> getEventsByDispatcher(Long dispatcherId) {
        return eventRepository.findByDispatcherId(dispatcherId).stream()
                .map(EmergencyEventDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmergencyEventDTO> getEventsBetweenTime(LocalDateTime startTime, LocalDateTime endTime) {
        return eventRepository.findEventsBetweenTime(startTime, endTime).stream()
                .map(EmergencyEventDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public EmergencyEventDTO assignDispatcher(Long eventId, Long dispatcherId) {
        EmergencyEvent event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        User dispatcher = userRepository.findById(dispatcherId)
                .orElseThrow(() -> new RuntimeException("Dispatcher not found"));
        
        event.setDispatcher(dispatcher);
        event.setStatus(EmergencyEvent.EventStatus.ASSIGNED);
        EmergencyEvent savedEvent = eventRepository.save(event);
        return EmergencyEventDTO.fromEntity(savedEvent);
    }

    @Override
    public EmergencyEventDTO updateEventStatus(Long eventId, EmergencyEvent.EventStatus status) {
        EmergencyEvent event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        event.setStatus(status);
        if (status == EmergencyEvent.EventStatus.RESOLVED) {
            event.setResolvedAt(LocalDateTime.now());
        }
        EmergencyEvent savedEvent = eventRepository.save(event);
        return EmergencyEventDTO.fromEntity(savedEvent);
    }

    @Override
    public EmergencyEventDTO resolveEvent(Long eventId, String remark) {
        EmergencyEvent event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        event.setStatus(EmergencyEvent.EventStatus.RESOLVED);
        event.setResolvedAt(LocalDateTime.now());
        event.setRemark(remark);
        EmergencyEvent savedEvent = eventRepository.save(event);
        return EmergencyEventDTO.fromEntity(savedEvent);
    }

    @Override
    public EmergencyEventDTO updateEvent(Long eventId, EmergencyEventDTO eventDTO) {
        EmergencyEvent event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        
        if (eventDTO.getTitle() != null) {
            event.setTitle(eventDTO.getTitle());
        }
        if (eventDTO.getDescription() != null) {
            event.setDescription(eventDTO.getDescription());
        }
        if (eventDTO.getLocation() != null) {
            event.setLocation(eventDTO.getLocation());
        }
        if (eventDTO.getLatitude() != null) {
            event.setLatitude(eventDTO.getLatitude());
        }
        if (eventDTO.getLongitude() != null) {
            event.setLongitude(eventDTO.getLongitude());
        }
        if (eventDTO.getPriority() != null) {
            event.setPriority(EmergencyEvent.EventPriority.valueOf(eventDTO.getPriority()));
        }
        
        EmergencyEvent savedEvent = eventRepository.save(event);
        return EmergencyEventDTO.fromEntity(savedEvent);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}
