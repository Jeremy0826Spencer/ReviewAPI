package com.example.Demo.Location;

import com.example.Demo.Location.*;
import com.example.Review.InvalidReviewDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(
        path = {"/api/v1/location"}
)
public class LocationController {
    private final LocationService locationService;

    @Autowired
    LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<Location>> getLocations() {
        List<Location> locations = this.locationService.getLocations();
        return ResponseEntity.ok(locations);
    }

    @PostMapping
    public ResponseEntity<?> createLocation(@RequestBody Location location) {
        try {
            Location createdLocation = this.locationService.createLocation(location);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
        } catch (InvalidReviewDataException var3) {
            return ResponseEntity.badRequest().body("Invalid location data: " + var3.getMessage());
        }
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Optional<Location> itemOptional = this.locationService.getLocationById(id);
        return itemOptional.isPresent() ? ResponseEntity.ok((Location)itemOptional.get()) : ResponseEntity.notFound().build();
    }

    @PutMapping({"/{locationId}"})
    public ResponseEntity<?> updateLocation(@PathVariable Long locationId, @RequestBody Location updatedLocation) {
        try {
            Location updatedReviewEntity = this.locationService.updateLocation(locationId, updatedLocation);
            return ResponseEntity.ok(updatedReviewEntity);
        } catch (LocationNotFoundException var4) {
            return ResponseEntity.notFound().build();
        }
    }
}

