package com.example.demo.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findById(Long id);

    List<Location> findByCity(String city);

    List<Location> findByLocationName(String locationName);

    List<Location> findByCityAndLocationName(String city, String locationName);

    @Query(value = "SELECT l.* FROM location l " +
            "JOIN review r ON l.location_id = r.review_location_id " +
            "WHERE l.city = :city " +
            "GROUP BY l.location_id " +
            "ORDER BY COUNT(r.review_id) DESC " +
            "LIMIT 1", nativeQuery = true)
    Optional<Location> findMostReviewedLocationInCity(@Param("city") String city);


}
