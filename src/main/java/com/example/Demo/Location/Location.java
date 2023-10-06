package com.example.Demo.Location;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
        name = "location"
)
public class Location {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long locationId;
    private String locationName;
    private String street;
    private String city;
    private int zip;
    private String state;

    public Location() {
    }

    public Long getLocationId() {
        return this.locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return this.locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZip() {
        return this.zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Location(String locationName) {
        this.locationName = locationName;
    }

    public Location(String locationName, String street, String city, int zip, String state) {
        this.locationName = locationName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.state = state;
    }

    public Location(Long locationId, String locationName, String street, String city, int zip, String state) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.state = state;
    }
}

