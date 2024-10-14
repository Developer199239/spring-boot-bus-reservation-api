package com.example.busreservation.repos;

import com.example.busreservation.entities.Bus;
import com.example.busreservation.entities.BusRoute;
import com.example.busreservation.entities.BusSchedule;
import com.example.busreservation.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Long> {
    Optional<List<BusSchedule>> findByBusRoute(BusRoute busRoute);
    Boolean existsByBusAndBusRouteAndDepartureTime(Bus bus, BusRoute busRoute, String date);
    Optional<BusSchedule> findByScheduleId(Long scheduleId);
}
