package com.example.busreservation.repos;

import com.example.busreservation.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByCityId(Long cityId);
}
