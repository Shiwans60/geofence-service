package com.example.geofence.repository;

import com.example.geofence.model.Zone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends MongoRepository<Zone, String> {
}
