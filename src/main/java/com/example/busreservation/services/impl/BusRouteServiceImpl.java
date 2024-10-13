package com.example.busreservation.services.impl;
import com.example.busreservation.entities.BusRoute;
import com.example.busreservation.entities.City;
import com.example.busreservation.models.ReservationApiException;
import com.example.busreservation.repos.BusRouteRepository;
import com.example.busreservation.services.BusRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusRouteServiceImpl implements BusRouteService {

    @Autowired
    private BusRouteRepository busRouteRepository;
    @Override
    public BusRoute addRoute(BusRoute busRoute) {
        return busRouteRepository.save(busRoute);
    }

    @Override
    public List<BusRoute> getAllBusRoutes() {
        return busRouteRepository.findAll();
    }

    @Override
    public BusRoute getRouteByRouteName(String routeName) {
        return busRouteRepository.findByRouteName(routeName).orElseThrow(() -> new ReservationApiException(HttpStatus.BAD_REQUEST, "No such route found!"));
    }

    @Override
    public BusRoute getRouteByCityFromAndCityTo(String cityFrom, String cityTo) {
        return busRouteRepository.findByCityFromAndCityTo(cityFrom, cityTo).orElseThrow(() -> new ReservationApiException(HttpStatus.BAD_REQUEST, "No such route found!"));
    }

    @Override
    public BusRoute updateBusRoute(Long id, BusRoute busRoute) {
        BusRoute existingRoute = busRouteRepository.findByRouteId(id)
                .orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "Route not found with id: " + id));
        existingRoute.setRouteName(busRoute.getRouteName());
        existingRoute.setCityFrom(busRoute.getCityFrom());
        existingRoute.setCityTo(busRoute.getCityTo());
        existingRoute.setDistanceInKm(busRoute.getDistanceInKm());
        return busRouteRepository.save(existingRoute);
    }
}
