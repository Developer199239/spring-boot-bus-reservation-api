package com.example.busreservation.services.impl;

import com.example.busreservation.entities.Bus;
import com.example.busreservation.entities.BusRoute;
import com.example.busreservation.entities.BusSchedule;
import com.example.busreservation.entities.City;
import com.example.busreservation.models.ReservationApiException;
import com.example.busreservation.repos.BusRepository;
import com.example.busreservation.repos.BusRouteRepository;
import com.example.busreservation.repos.BusScheduleRepository;
import com.example.busreservation.services.BusScheduleService;
import com.example.busreservation.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BusScheduleServiceImpl implements BusScheduleService {
    @Autowired
    private BusScheduleRepository busScheduleRepository;

    @Autowired
    private BusRouteRepository busRouteRepository;

    @Autowired
    private BusRepository busRepository;


    @Override
    public BusSchedule addSchedule(BusSchedule busSchedule) throws ReservationApiException{
        Bus bus = busRepository.findByBusId(busSchedule.getBus().getBusId())
                .orElseThrow(() -> new ReservationApiException(HttpStatus.BAD_REQUEST, "Bus not found"));
        BusRoute busRoute = busRouteRepository.findById(busSchedule.getBusRoute().getRouteId())
                .orElseThrow(() -> new ReservationApiException(HttpStatus.BAD_REQUEST, "Bus route not found"));

        busSchedule.setBus(bus);
        busSchedule.setBusRoute(busRoute);
        final boolean exists =
                busScheduleRepository.existsByBusAndBusRouteAndDepartureTime(
                        busSchedule.getBus(),
                        busSchedule.getBusRoute(),
                        busSchedule.getDepartureTime());
        if(exists) {
            throw new ReservationApiException(HttpStatus.CONFLICT, "Duplicate Schedule");
        }
        return busScheduleRepository.save(busSchedule);
    }

    @Override
    public List<BusSchedule> getAllBusSchedules() {
        return busScheduleRepository.findAll();
    }

    @Override
    public List<BusSchedule> getSchedulesByRoute(String routeName) {
        final BusRoute busRoute = busRouteRepository.findByRouteName(routeName).orElseThrow(() -> new ReservationApiException(HttpStatus.BAD_REQUEST, "Not Found"));
        return busScheduleRepository.findByBusRoute(busRoute).orElseThrow(() -> new ReservationApiException(HttpStatus.BAD_REQUEST, "Not Found"));
    }

    @Override
    public BusSchedule updateBusSchedule(Long id, BusSchedule busSchedule) {
        BusSchedule existingBusSchedule = busScheduleRepository.findByScheduleId(id)
                .orElseThrow(() -> new ReservationApiException(HttpStatus.NOT_FOUND, "Schedule not found with id: " + id));
        existingBusSchedule.setBus(busSchedule.getBus());
        existingBusSchedule.setBusRoute(busSchedule.getBusRoute());
        existingBusSchedule.setDepartureTime(busSchedule.getDepartureTime());
        existingBusSchedule.setTicketPrice(busSchedule.getTicketPrice());
        existingBusSchedule.setDiscount(busSchedule.getDiscount());
        existingBusSchedule.setProcessingFee(busSchedule.getProcessingFee());
        return busScheduleRepository.save(existingBusSchedule);
    }
}