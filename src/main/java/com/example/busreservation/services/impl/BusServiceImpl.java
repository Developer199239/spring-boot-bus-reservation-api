package com.example.busreservation.services.impl;

import com.example.busreservation.entities.Bus;
import com.example.busreservation.entities.City;
import com.example.busreservation.models.ReservationApiException;
import com.example.busreservation.repos.BusRepository;
import com.example.busreservation.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService {
    @Autowired
    private BusRepository busRepository;

    @Override
    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }


    @Override
    public List<Bus> getAllBus() {
        return busRepository.findAll();
    }

    @Override
    public Bus updateBus(Long id, Bus busDetails) {
        Bus existingBus = busRepository.findByBusId(id)
                .orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "Bus not found with id: " + id));
        existingBus.setBusNumber(busDetails.getBusNumber());
        existingBus.setBusName(busDetails.getBusName());
        existingBus.setBusType(busDetails.getBusType());
        return busRepository.save(existingBus);
    }

    @Override
    public void deleteBus(Long id) {
        Bus existingBus = busRepository.findByBusId(id)
                .orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "But not found with id: " + id));
        busRepository.delete(existingBus);
    }
}
