package com.example.demo.Location;

import com.example.demo.Review.InvalidReviewDataException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("public/api/v1/location")
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
    public ResponseEntity<?> createLocation(@Valid @RequestBody Location location) {
        try {
            Location createdLocation = this.locationService.createLocation(location);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
        } catch (InvalidReviewDataException ex) {
            return ResponseEntity.badRequest().body("Invalid location data: " + ex.getMessage());
        }
    }

    @GetMapping("/locationById/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Optional<Location> itemOptional = this.locationService.getLocationById(id);
        return itemOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/locationByCity/{city}")
    public ResponseEntity<List<Location>> getLocationByCity(@PathVariable String city) {
        List<Location> locations = this.locationService.getLocationsByCity(city);
        return locations.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(locations);
    }

    @GetMapping("/locationByName/{name}")
    public ResponseEntity<List<Location>> getLocationByName(@PathVariable String name) {
        List<Location> locations = this.locationService.getLocationsByName(name);
        return locations.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(locations);
    }

    @GetMapping("/mostReviewedLocationByCity")
    public ResponseEntity<Location> getMostReviewedLocationByCity(@RequestParam String city) {
        Location mostReviewedLocation = locationService.findMostReviewedLocationByCity(city);
        if (mostReviewedLocation != null) {
            return ResponseEntity.ok(mostReviewedLocation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @GetMapping("/locationByBoth")
    public ResponseEntity<List<Location>> getLocationByCityAndName(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String locationName) {

        // Ensure both city and locationName are provided
        if (city == null || locationName == null) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        List<Location> locations = this.locationService.findByCityAndLocationName(city, locationName);
        if (locations.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(locations);
        }
    }



    // Helper method to convert list of locations to JSON string (you may use a library like Gson or Jackson)
    private String convertLocationsToJson(List<Location> locations) {
        // Implement logic to convert list of Location objects to JSON string
        // Example: Using Gson library
        Gson gson = new Gson();
        return gson.toJson(locations);
    }


    @PutMapping("/{locationId}")
    public ResponseEntity<?> updateLocation(@PathVariable Long locationId, @Valid @RequestBody Location updatedLocation) {
        try {
            Location updatedLocationEntity = this.locationService.updateLocation(locationId, updatedLocation);
            return ResponseEntity.ok(updatedLocationEntity);
        } catch (LocationNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
