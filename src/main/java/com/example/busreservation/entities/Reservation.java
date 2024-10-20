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
@Table(name = "bus_reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUsers appUser;

    @ManyToOne
    @JoinColumn(name = "bus_schedule_id", nullable = false)
    private BusSchedule busSchedule;
    private Long timestamp;
    private String departureDate;
    private Integer totalSeatBooked;
    private String seatNumbers;
    private String reservationStatus;
    private Integer totalPrice;

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", appUser=" + appUser +
                ", busSchedule=" + busSchedule +
                ", timestamp=" + timestamp +
                ", departureDate='" + departureDate + '\'' +
                ", totalSeatBooked=" + totalSeatBooked +
                ", seatNumbers='" + seatNumbers + '\'' +
                ", reservationStatus='" + reservationStatus + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
