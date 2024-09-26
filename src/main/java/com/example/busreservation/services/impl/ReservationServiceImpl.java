package com.example.busreservation.services.impl;

import com.example.busreservation.entities.BusSchedule;
import com.example.busreservation.entities.Customer;
import com.example.busreservation.entities.Reservation;
import com.example.busreservation.models.ReservationApiException;
import com.example.busreservation.repos.BusScheduleRepository;
import com.example.busreservation.repos.CustomerRepository;
import com.example.busreservation.repos.ReservationRepository;
import com.example.busreservation.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BusScheduleRepository busScheduleRepository;
    @Override
    public Reservation addReservation(Reservation reservation) {
        // Try to fetch the customer by mobile or email in a single database call
        Customer customer = customerRepository
                .findByMobileOrEmail(reservation.getCustomer().getMobile(), reservation.getCustomer().getEmail())
                .orElseGet(() -> customerRepository.save(reservation.getCustomer()));

        reservation.setCustomer(customer);

        // Check if the reservation already exists for the customer, bus schedule, and departure date
        Optional<Reservation> existingReservation = reservationRepository
                .findByCustomerAndBusScheduleAndDepartureDate(
                        reservation.getCustomer(),
                        reservation.getBusSchedule(),
                        reservation.getDepartureDate());

        if (existingReservation.isPresent()) {
            Reservation existingRes = existingReservation.get();

            // Append the new seat numbers to the existing seat numbers while avoiding duplicates
            String existingSeats = existingRes.getSeatNumbers();
            String newSeats = reservation.getSeatNumbers();

            // Assuming seat numbers are comma-separated, split and concatenate them while avoiding duplicates
            Set<String> seatSet = new LinkedHashSet<>();
            if (existingSeats != null && !existingSeats.isEmpty()) {
                seatSet.addAll(Arrays.asList(existingSeats.split(",")));
            }
            if (newSeats != null && !newSeats.isEmpty()) {
                seatSet.addAll(Arrays.asList(newSeats.split(",")));
            }

            existingRes.setSeatNumbers(String.join(",", seatSet));

            // Update the total seats booked and total price
            existingRes.setTotalSeatBooked(existingRes.getTotalSeatBooked() + reservation.getTotalSeatBooked());
            existingRes.setTotalPrice(existingRes.getTotalPrice() + reservation.getTotalPrice());

            // Log the update action to verify it's getting here
            System.out.println("Updating existing reservation with ID: " + existingRes.getReservationId());

            // Save the updated reservation
            return reservationRepository.save(existingRes);
        } else {
            // Log the creation action
            System.out.println("Creating a new reservation");

            // If no existing reservation, create a new one
            return reservationRepository.save(reservation);
        }
    }


//    public Reservation addReservation(Reservation reservation) {
//        final Customer customer;
//
//        // Check if the customer already exists based on mobile or email
//        final boolean doesCustomerExist = customerRepository
//                .existsByMobileOrEmail(reservation.getCustomer().getMobile(), reservation.getCustomer().getEmail());
//
//        if(doesCustomerExist) {
//            customer = customerRepository
//                    .findByMobileOrEmail(reservation.getCustomer().getMobile(), reservation.getCustomer().getEmail())
//                    .orElseThrow();
//        } else {
//            customer = customerRepository.save(reservation.getCustomer());
//        }
//
//        reservation.setCustomer(customer);
//
//        // Check if the reservation already exists to avoid a duplicate entry
//        Optional<Reservation> existingReservation = reservationRepository
//                .findByCustomerAndBusScheduleAndDepartureDate(
//                        reservation.getCustomer(),
//                        reservation.getBusSchedule(),
//                        reservation.getDepartureDate());
//
//        if (existingReservation.isPresent()) {
//            Reservation existingRes = existingReservation.get();
//            // Append the new seat numbers to the existing seat numbers (assuming seatNumbers is a comma-separated string)
//            String existingSeats = existingRes.getSeatNumbers();
//            String newSeats = reservation.getSeatNumbers();
//
//            if (existingSeats != null && !existingSeats.isEmpty()) {
//                existingSeats += "," + newSeats; // Concatenate new seat numbers
//            } else {
//                existingSeats = newSeats; // No previous seats booked
//            }
//
//            existingRes.setSeatNumbers(existingSeats);
//
//            // Update the total seats booked and other details if necessary
//            existingRes.setTotalSeatBooked(existingRes.getTotalSeatBooked() + reservation.getTotalSeatBooked());
//            existingRes.setTotalPrice(existingRes.getTotalPrice() + reservation.getTotalPrice());
//
//            // Save the updated reservation
//            return reservationRepository.save(existingRes);
//        }
//
//        // If no existing reservation, create a new one
//        return reservationRepository.save(reservation);
//    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsByScheduleAndDepartureDate(Long scheduleId, String departureDate) {
        final BusSchedule schedule = busScheduleRepository
                .findById(scheduleId)
                .orElseThrow(() -> new ReservationApiException(HttpStatus.BAD_REQUEST, "Schedule not found"));
        return reservationRepository
                .findByBusScheduleAndDepartureDate(schedule, departureDate)
                .orElseThrow(() -> new ReservationApiException(HttpStatus.BAD_REQUEST, "Reservation not found"));
    }

    @Override
    public List<Reservation> getReservationsByMobile(String mobile) {
        final Customer customer = customerRepository
                .findByMobile(mobile)
                .orElseThrow(() -> new ReservationApiException(HttpStatus.BAD_REQUEST, "No record found"));
        return reservationRepository.findByCustomer(customer).orElseThrow(() -> new ReservationApiException(HttpStatus.BAD_REQUEST, "No record found"));
    }
}

