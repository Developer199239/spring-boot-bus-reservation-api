package com.example.busreservation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bus_schedule")
public class BusSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    // A bus can have multiple schedules, so use ManyToOne
    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    private Bus bus;

    // A route can have multiple schedules, so use ManyToOne
    @ManyToOne
    @JoinColumn(name = "bus_route_id", nullable = false)
    private BusRoute busRoute;

    private String departureTime;
    private Integer ticketPrice;
    private Integer discount;
    private Integer processingFee;
}
