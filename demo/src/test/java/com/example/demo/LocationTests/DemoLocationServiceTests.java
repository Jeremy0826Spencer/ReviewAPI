package com.example.demo.LocationTests;
/*
import com.example.demo.Location.Location;
import com.example.demo.Location.LocationRepository;
import com.example.demo.Location.LocationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@TestPropertySource(
        locations = {"classpath:application-test.properties"}
)
@WithMockUser(username="admin", roles={"ADMIN"})
class DemoLocationServiceTests {
    @Autowired
    private LocationService locationService;
    @MockBean
    private LocationRepository locationRepository;
    private Location newLocation;
    private Location newLocation2;

    DemoLocationServiceTests() {
    }

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void setUp() {
        this.newLocation = new Location(3L, "Burger King", "456 S Ave", "Somewhere", 12345, "Nowhere");
        Mockito.when((Location)this.locationRepository.save((Location)Mockito.any(Location.class))).thenReturn(this.newLocation);
        this.newLocation2 = new Location("Wendy's", "123 N St", "Foley", 36526, "Alabama");
        Mockito.when((Location)this.locationRepository.save((Location)Mockito.any(Location.class))).thenReturn(this.newLocation);
    }

    @Test
    public void getLocationsTest() {
        List<Location> mockLocations = (List) Stream.of(this.newLocation, this.newLocation2).collect(Collectors.toList());
        Mockito.when(this.locationRepository.findAll()).thenReturn(mockLocations);
        List<Location> locations = this.locationService.getLocations();
        Assertions.assertEquals(2, locations.size());
    }

    @Test
    public void createLocationTest() {
        Location createdLocation = this.locationService.createLocation(this.newLocation);
        ((LocationRepository)Mockito.verify(this.locationRepository, Mockito.times(1))).save((Location)Mockito.any(Location.class));
        Assertions.assertEquals(this.newLocation, createdLocation);
    }
}

*/