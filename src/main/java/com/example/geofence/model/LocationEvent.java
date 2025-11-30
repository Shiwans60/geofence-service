package com.example.geofence.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "location_events")
public class LocationEvent {
    @Id
    private String id;
    private UUID vehicleId;
    private double latitude;
    private double longitude;
    private Instant timestamp;

    public LocationEvent() {}
    public LocationEvent(UUID vehicleId, double latitude, double longitude, Instant timestamp) {
        this.vehicleId = vehicleId; this.latitude = latitude; this.longitude = longitude; this.timestamp = timestamp;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public UUID getVehicleId() { return vehicleId; }
    public void setVehicleId(UUID vehicleId) { this.vehicleId = vehicleId; }
    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }
    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}
