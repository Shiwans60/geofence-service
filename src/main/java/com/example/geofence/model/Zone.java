package com.example.geofence.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@Document(collection = "zones")
public class Zone {
    @Id
    private String id;
    private String name;
    private List<GeoPoint> polygon;
    private String description;

    public Zone() {}
    public Zone(String id, String name, List<GeoPoint> polygon) {
        this.id = id; this.name = name; this.polygon = polygon;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<GeoPoint> getPolygon() { return polygon; }
    public void setPolygon(List<GeoPoint> polygon) { this.polygon = polygon; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
