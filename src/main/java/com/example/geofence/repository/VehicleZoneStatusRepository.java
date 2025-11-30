package com.example.geofence.repository;

import com.example.geofence.model.VehicleZoneStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface VehicleZoneStatusRepository extends MongoRepository<VehicleZoneStatus, String> {
    Optional<VehicleZoneStatus> findByVehicleId(UUID vehicleId);
}
