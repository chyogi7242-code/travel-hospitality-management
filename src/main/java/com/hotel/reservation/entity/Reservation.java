package com.hotel.reservation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    @Column(name = "check_in_date")
    private LocalDate checkInDate;


    @Column(name = "check_out_date")
    private LocalDate checkOutDate;


    @Column(name = "number_of_guests")
    private Integer numberOfGuests;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationStatus status;
}