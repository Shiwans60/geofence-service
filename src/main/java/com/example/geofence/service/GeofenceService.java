package com.example.geofence.service;

import com.example.geofence.model.*;
import com.example.geofence.repository.LocationEventRepository;
import com.example.geofence.repository.VehicleZoneStatusRepository;
import com.example.geofence.repository.ZoneRepository;
import com.example.geofence.util.GeoUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GeofenceService {
    private final ZoneRepository zoneRepo;
    private final LocationEventRepository eventRepo;
    private final VehicleZoneStatusRepository statusRepo;
    private final Logger log = LoggerFactory.getLogger(GeofenceService.class);

    public GeofenceService(ZoneRepository zoneRepo,
                           LocationEventRepository eventRepo,
                           VehicleZoneStatusRepository statusRepo) {
        this.zoneRepo = zoneRepo;
        this.eventRepo = eventRepo;
        this.statusRepo = statusRepo;
    }

    public void processLocationEvent(LocationEvent event) {
        eventRepo.save(event);

        UUID vid = event.getVehicleId();
        GeoPoint pt = new GeoPoint(event.getLatitude(), event.getLongitude());

        List<Zone> zones = zoneRepo.findAll();
        Optional<Zone> matched = zones.stream()
                .filter(z -> GeoUtils.pointInPolygon(pt, z.getPolygon()))
                .min(Comparator.comparingInt(z -> z.getPolygon().size()));

        Optional<VehicleZoneStatus> currentOpt = statusRepo.findByVehicleId(vid);
        VehicleZoneStatus status = currentOpt.orElseGet(() -> {
            VehicleZoneStatus s = new VehicleZoneStatus();
            s.setVehicleId(vid);
            return s;
        });

        String newZoneId = matched.map(Zone::getId).orElse(null);
        String newZoneName = matched.map(Zone::getName).orElse(null);
        String prevZoneId = status.getCurrentZoneId();

        if (prevZoneId == null && newZoneId != null) {
            status.setCurrentZoneId(newZoneId);
            status.setCurrentZoneName(newZoneName);
            status.setEnteredAt(Instant.now());
            log.info("Vehicle {} ENTERED zone {} ({})", vid, newZoneId, newZoneName);
        } else if (prevZoneId != null && (newZoneId == null || !prevZoneId.equals(newZoneId))) {
            log.info("Vehicle {} EXITED zone {}", vid, prevZoneId);
            status.setCurrentZoneId(newZoneId);
            status.setCurrentZoneName(newZoneName);
            status.setEnteredAt(newZoneId == null ? null : Instant.now());
            if (newZoneId != null) {
                log.info("Vehicle {} ENTERED zone {} ({})", vid, newZoneId, newZoneName);
            }
        } else {
        }

        status.setLastLat(event.getLatitude());
        status.setLastLon(event.getLongitude());
        status.setUpdatedAt(Instant.now());

        statusRepo.save(status);
    }

    public Optional<VehicleZoneStatus> getVehicleStatus(UUID vehicleId) {
        return statusRepo.findByVehicleId(vehicleId);
    }
}
