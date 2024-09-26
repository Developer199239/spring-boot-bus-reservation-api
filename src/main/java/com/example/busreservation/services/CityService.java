package com.example.busreservation.services;
import com.example.busreservation.entities.City;

import java.util.List;

public interface CityService {
    City addCity(City city);
    List<City> getAllCity();
    City updateCity(Long id, City cityDetails);
    void deleteCity(Long id);
}
