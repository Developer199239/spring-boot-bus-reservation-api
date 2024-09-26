package com.example.busreservation.services.impl;

import com.example.busreservation.entities.City;
import com.example.busreservation.models.ReservationApiException;
import com.example.busreservation.repos.CityRepository;
import com.example.busreservation.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepository cityRepository;

    @Override
    public City addCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    @Override
    public City updateCity(Long id, City cityDetails) {
        City existingCity = cityRepository.findByCityId(id)
                .orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "City not found with id: " + id));
        existingCity.setCityName(cityDetails.getCityName());
        return cityRepository.save(existingCity);
    }

    @Override
    public void deleteCity(Long id) {
        City existingCity = cityRepository.findByCityId(id)
                .orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "City not found with id: " + id));
        cityRepository.delete(existingCity);
    }
}
