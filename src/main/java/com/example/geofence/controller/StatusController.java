package com.example.geofence.controller;

import com.example.geofence.dto.ZoneStatusResponse;
import com.example.geofence.model.VehicleZoneStatus;
import com.example.geofence.service.GeofenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/vehicles")
public class StatusController {
    private final GeofenceService geofenceService;

    public StatusController(GeofenceService geofenceService) {
        this.geofenceService = geofenceService;
    }

    @GetMapping("/{vehicleId}/status")
    public ResponseEntity<ZoneStatusResponse> getStatus(@PathVariable("vehicleId") UUID vehicleId) {
        Optional<VehicleZoneStatus> opt = geofenceService.getVehicleStatus(vehicleId);
        if (!opt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        VehicleZoneStatus s = opt.get();
        ZoneStatusResponse resp = new ZoneStatusResponse();
        resp.setVehicleId(s.getVehicleId());
        resp.setZoneId(s.getCurrentZoneId());
        resp.setZoneName(s.getCurrentZoneName());
        resp.setEnteredAt(s.getEnteredAt());
        return ResponseEntity.ok(resp);
    }
}
