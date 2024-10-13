package com.example.busreservation.services;

import com.example.busreservation.entities.Bus;
import com.example.busreservation.entities.City;

import java.util.List;

public interface BusService {
    Bus addBus(Bus bus);
    List<Bus> getAllBus();
    Bus updateBus(Long id, Bus busDetails);
    void deleteBus(Long id);
}
