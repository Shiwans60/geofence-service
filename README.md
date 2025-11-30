Geofence Service

A monolithic Spring Boot and MongoDB Atlas based geofencing service that processes vehicle location events and determines zone enter/exit status using polygon boundaries.

Tech Stack

Java 21

Spring Boot 4

Spring Web

Spring Data MongoDB

Spring Boot Actuator

MongoDB Atlas

Maven

Setup Instructions
Clone the Repository
git clone https://github.com/<your-username>/geofence-service.git
cd geofence-service

Configure MongoDB Atlas

Edit src/main/resources/application.yaml:

spring:
  application:
    name: geofence

  data:
    mongodb:
      uri: "mongodb+srv://<USERNAME>:<PASSWORD>@<CLUSTER>.mongodb.net/geofence?retryWrites=true&w=majority"
      database: geofence

server:
  port: 8080

logging:
  level:
    root: INFO
    com.example.geofence: DEBUG

Build and Run
mvn clean install
mvn spring-boot:run


Application URL:

http://localhost:8080

API Endpoints
Create Geofence Zone

POST /zones

{
  "id": "AirportZone",
  "name": "Airport Area",
  "description": "Drop-off/Pickup Zone",
  "polygon": [
    { "lat": 12.9710, "lon": 77.5930 },
    { "lat": 12.9720, "lon": 77.5980 },
    { "lat": 12.9680, "lon": 77.6000 },
    { "lat": 12.9660, "lon": 77.5950 }
  ]
}

Ingest Vehicle Location Event

POST /events

{
  "vehicleId": "c2b932e2-06f1-4f1c-bbe2-c33bdcbdb67c",
  "latitude": 12.9718,
  "longitude": 77.5941,
  "timestamp": "2025-11-29T15:10:00Z"
}

Get Vehicle Current Zone

GET /vehicles/{vehicleId}/status

Example:

/vehicles/c2b932e2-06f1-4f1c-bbe2-c33bdcbdb67c/status


Sample Response:

{
  "vehicleId": "c2b932e2-06f1-4f1c-bbe2-c33bdcbdb67c",
  "zoneId": "AirportZone",
  "zoneName": "Airport Area",
  "enteredAt": "2025-11-29T15:10:02.321Z"
}

Assumptions

Zones are defined as ordered polygon coordinate lists.

Vehicle events may arrive at any time; latest event updates status.

Overlapping zones return the first matched zone.

Point-on-boundary counts as inside.

MongoDB stores zones, location events, and vehicle zone status.

Design Decisions

Monolithic architecture for simplicity.

MongoDB Atlas for flexible geospatial document storage.

Cached polygon evaluation using ray-casting.

Separate collection for vehicle zone status.

Layered structure: controller, service, repository, model, utility.

Improvements With More Time

Real-time updates using WebSocket or Server-Sent Events.

JWT security.

MongoDB GeoJSON queries for spherical accuracy.

Rate limiting and event deduplication.

Unit and integration tests.

Docker-compose setup.

Zone priority handling.
