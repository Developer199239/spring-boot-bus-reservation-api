package com.example.busreservation.repos;

import com.example.busreservation.entities.Bus;
import com.example.busreservation.entities.BusRoute;
import com.example.busreservation.entities.BusSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findByBusId(Long busId);
}
