package com.example.busreservation.repos;

import com.example.busreservation.entities.BusRoute;
import com.example.busreservation.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusRouteRepository extends JpaRepository<BusRoute, Long> {
    Optional<BusRoute> findByRouteName(String routeName);
    Optional<BusRoute> findByCityFromAndCityTo(String cityFrom, String cityTo);
    Optional<BusRoute> findByRouteId(Long RouteId);
}
