package com.example.geofence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "vehicle_zone_status")
public class VehicleZoneStatus {
    @Id
    private String id;
    private UUID vehicleId;
    private String currentZoneId; // null when outside any zone
    private String currentZoneName;
    private Instant enteredAt;
    private double lastLat;
    private double lastLon;
    private Instant updatedAt;

    public VehicleZoneStatus() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public UUID getVehicleId() { return vehicleId; }
    public void setVehicleId(UUID vehicleId) { this.vehicleId = vehicleId; }
    public String getCurrentZoneId() { return currentZoneId; }
    public void setCurrentZoneId(String currentZoneId) { this.currentZoneId = currentZoneId; }
    public String getCurrentZoneName() { return currentZoneName; }
    public void setCurrentZoneName(String currentZoneName) { this.currentZoneName = currentZoneName; }
    public Instant getEnteredAt() { return enteredAt; }
    public void setEnteredAt(Instant enteredAt) { this.enteredAt = enteredAt; }
    public double getLastLat() { return lastLat; }
    public void setLastLat(double lastLat) { this.lastLat = lastLat; }
    public double getLastLon() { return lastLon; }
    public void setLastLon(double lastLon) { this.lastLon = lastLon; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}

