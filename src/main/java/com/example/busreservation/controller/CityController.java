package com.example.busreservation.controller;

import com.example.busreservation.entities.City;
import com.example.busreservation.models.ResponseModel;
import com.example.busreservation.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/city")
public class CityController {
    @Autowired
    private CityService cityService;

    @PostMapping("/add")
    public ResponseModel<City> addCity(@RequestBody City city) {
        final City savedCity = cityService.addCity(city);
        return new ResponseModel<>(HttpStatus.OK.value(), "City saved", savedCity);
    }

    @GetMapping("/all")
    public ResponseEntity<List<City>> getAllCity() {
        return ResponseEntity.ok(cityService.getAllCity());
    }

    @PutMapping("/update/{id}")
    public ResponseModel<City> updateCity(@PathVariable Long id, @RequestBody City city) {
        final City updatedCity = cityService.updateCity(id, city);
        return new ResponseModel<>(HttpStatus.OK.value(), "City updated", updatedCity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseModel<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return new ResponseModel<>(HttpStatus.OK.value(), "City deleted", null);
    }
}
