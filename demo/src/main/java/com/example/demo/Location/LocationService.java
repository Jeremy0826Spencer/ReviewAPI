package com.example.demo.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getLocations() {
        return this.locationRepository.findAll();
    }

    public Location createLocation(@RequestBody Location location) {
        Location loc = (Location)this.locationRepository.save(location);
        return loc;
    }

    public Optional<Location> getLocationById(Long id) {
        return this.locationRepository.findById(id);
    }

    public Location updateLocation(Long locationId, Location updatedLocation) throws LocationNotFoundException {
        Optional<Location> optionalLocation = this.locationRepository.findById(locationId);
        if (optionalLocation.isPresent()) {
            Location existingLocation = (Location)optionalLocation.get();
            existingLocation.setLocationName(updatedLocation.getLocationName());
            return (Location)this.locationRepository.save(existingLocation);
        } else {
            throw new LocationNotFoundException("Review with ID " + locationId + " not found");
        }
    }
}
