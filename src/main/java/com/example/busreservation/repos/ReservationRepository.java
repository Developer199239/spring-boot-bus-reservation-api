package com.example.busreservation.repos;

import com.example.busreservation.entities.AppUsers;
import com.example.busreservation.entities.BusSchedule;
import com.example.busreservation.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<List<Reservation>> findByAppUser(AppUsers appUsers);
//    Optional<List<Reservation>> findByAppUser(String userId);
    Optional<List<Reservation>> findByBusScheduleAndDepartureDate(BusSchedule busSchedule, String departureDate);
    Optional<Reservation> findByAppUserAndBusScheduleAndDepartureDate(
            AppUsers appUsers,
            BusSchedule busSchedule,
            String departureDate);

}
