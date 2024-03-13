package com.example.demo.LocationTests;
/*
import com.example.demo.Location.Location;
import com.example.demo.Location.LocationController;
import com.example.demo.Location.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@WebMvcTest({LocationController.class})
@TestPropertySource(
        locations = {"classpath:application-test.properties"}
)
@AutoConfigureMockMvc
@WithMockUser(username="admin", roles={"ADMIN"})
public class DemoLocationControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LocationService locationService;

    public DemoLocationControllerTests() {
    }

    @BeforeEach
    public void setUp() {
        Mockito.when(this.locationService.getLocations()).thenReturn(Arrays.asList(new Location(1L, "City A", "Street A", "City A", 12345, "State A"), new Location(2L, "City B", "Street B", "City B", 67890, "State B")));
    }

    @Test
    public void givenLocations_whenGetAllLocations_thenReturnLocationList() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/location", new Object[0]))
                .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.length()", new Object[0])
                        .value(2)).andExpect(MockMvcResultMatchers.jsonPath("$[0].locationId", new Object[0])
                        .value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].locationName", new Object[0]).value("City A"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].street", new Object[0])
                        .value("Street A")).andExpect(MockMvcResultMatchers.jsonPath("$[0].city", new Object[0])
                        .value("City A")).andExpect(MockMvcResultMatchers.jsonPath("$[0].zip", new Object[0])
                        .value(12345)).andExpect(MockMvcResultMatchers.jsonPath("$[0].state", new Object[0])
                        .value("State A")).andExpect(MockMvcResultMatchers.jsonPath("$[1].locationId", new Object[0])
                        .value(2)).andExpect(MockMvcResultMatchers.jsonPath("$[1].locationName", new Object[0])
                        .value("City B")).andExpect(MockMvcResultMatchers.jsonPath("$[1].street", new Object[0])
                        .value("Street B")).andExpect(MockMvcResultMatchers.jsonPath("$[1].city", new Object[0])
                        .value("City B")).andExpect(MockMvcResultMatchers.jsonPath("$[1].zip", new Object[0])
                        .value(67890)).andExpect(MockMvcResultMatchers.jsonPath("$[1].state", new Object[0])
                        .value("State B"));
    }
}

*/