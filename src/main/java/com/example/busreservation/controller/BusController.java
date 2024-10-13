package com.example.busreservation.controller;

import com.example.busreservation.entities.Bus;
import com.example.busreservation.entities.City;
import com.example.busreservation.models.ResponseModel;
import com.example.busreservation.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/bus")
public class BusController {
    @Autowired
    private BusService busService;

    @PostMapping("/add")
    public ResponseModel<Bus> addBus(@RequestBody Bus bus) {
        final Bus savedBus = busService.addBus(bus);
        return new ResponseModel<>(HttpStatus.OK.value(), "Bus saved", savedBus);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Bus>> getAllBus() {
        return ResponseEntity.ok(busService.getAllBus());
    }

    @PutMapping("/update/{id}")
    public ResponseModel<Bus> updateBus(@PathVariable Long id, @RequestBody Bus bus) {
        final Bus updatedBus = busService.updateBus(id, bus);
        return new ResponseModel<>(HttpStatus.OK.value(), "Bus updated", updatedBus);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseModel<Void> deleteCity(@PathVariable Long id) {
        busService.deleteBus(id);
        return new ResponseModel<>(HttpStatus.OK.value(), "Bus deleted", null);
    }
}
