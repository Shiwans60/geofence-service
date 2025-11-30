package com.example.geofence.dto;

import java.time.Instant;
import java.util.UUID;

public class ZoneStatusResponse {
    private UUID vehicleId;
    private String zoneId;
    private String zoneName;
    private Instant enteredAt;

    public UUID getVehicleId() { return vehicleId; }
    public void setVehicleId(UUID vehicleId) { this.vehicleId = vehicleId; }
    public String getZoneId() { return zoneId; }
    public void setZoneId(String zoneId) { this.zoneId = zoneId; }
    public String getZoneName() { return zoneName; }
    public void setZoneName(String zoneName) { this.zoneName = zoneName; }
    public Instant getEnteredAt() { return enteredAt; }
    public void setEnteredAt(Instant enteredAt) { this.enteredAt = enteredAt; }
}
