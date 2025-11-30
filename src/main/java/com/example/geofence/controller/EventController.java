package com.example.geofence.controller;

import com.example.geofence.dto.EventRequest;
import com.example.geofence.model.LocationEvent;
import com.example.geofence.service.GeofenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
    private final GeofenceService geofenceService;
    private final Logger log = LoggerFactory.getLogger(EventController.class);

    public EventController(GeofenceService geofenceService) {
        this.geofenceService = geofenceService;
    }

    @PostMapping
    public ResponseEntity<String> receiveEvent(@RequestBody EventRequest req) {
        if (req.getVehicleId() == null) {
            return ResponseEntity.badRequest().body("vehicleId required");
        }
        if (req.getTimestamp() == null) {
            req.setTimestamp(java.time.Instant.now());
        }
        LocationEvent ev = new LocationEvent(req.getVehicleId(), req.getLatitude(), req.getLongitude(), req.getTimestamp());
        try {
            geofenceService.processLocationEvent(ev);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("accepted");
        } catch (Exception e) {
            log.error("error processing event", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }
}
