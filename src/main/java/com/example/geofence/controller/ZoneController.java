package com.example.geofence.controller;

import com.example.geofence.model.Zone;
import com.example.geofence.repository.ZoneRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zones")
public class ZoneController {

    private final ZoneRepository zoneRepo;

    public ZoneController(ZoneRepository zoneRepo) {
        this.zoneRepo = zoneRepo;
    }

    @PostMapping
    public ResponseEntity<?> createZone(@RequestBody Zone zone) {
        try {
            zoneRepo.save(zone);
            return ResponseEntity.ok("Zone created successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error saving zone: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Zone>> getAllZones() {
        return ResponseEntity.ok(zoneRepo.findAll());
    }
}
