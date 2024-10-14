package com.example.busreservation.services;
import com.example.busreservation.entities.BusSchedule;
import com.example.busreservation.entities.City;

import java.util.List;

public interface BusScheduleService {
    BusSchedule addSchedule(BusSchedule busSchedule);
    List<BusSchedule> getAllBusSchedules();
    List<BusSchedule> getSchedulesByRoute(String routeName);
    BusSchedule updateBusSchedule(Long id, BusSchedule busSchedule);
}
