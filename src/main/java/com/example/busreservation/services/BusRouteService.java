package com.example.busreservation.services;

import com.example.busreservation.entities.BusRoute;
import com.example.busreservation.entities.City;

import java.util.List;

public interface BusRouteService {
    BusRoute addRoute(BusRoute busRoute);
    List<BusRoute> getAllBusRoutes();

    BusRoute getRouteByRouteName(String routeName);

    BusRoute getRouteByCityFromAndCityTo(String cityFrom, String cityTo);

    BusRoute updateBusRoute(Long id, BusRoute busRoute);
}
