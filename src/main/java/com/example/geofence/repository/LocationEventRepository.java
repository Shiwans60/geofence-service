package com.example.geofence.repository;

import com.example.geofence.model.LocationEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface LocationEventRepository extends MongoRepository<LocationEvent, String> {
    List<LocationEvent> findByVehicleIdOrderByTimestampDesc(UUID vehicleId);
}
