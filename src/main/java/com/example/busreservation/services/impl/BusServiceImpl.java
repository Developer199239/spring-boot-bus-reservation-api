package com.example.busreservation.services.impl;

import com.example.busreservation.entities.Bus;
import com.example.busreservation.repos.BusRepository;
import com.example.busreservation.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
