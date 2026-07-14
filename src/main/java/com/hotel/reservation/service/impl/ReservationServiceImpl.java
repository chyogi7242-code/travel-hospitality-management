package com.hotel.reservation.service.impl;

import com.hotel.reservation.dto.ReservationRequest;
import com.hotel.reservation.dto.ReservationResponse;
import com.hotel.reservation.entity.Reservation;
import com.hotel.reservation.entity.ReservationStatus;
import com.hotel.reservation.repository.ReservationRepository;
import com.hotel.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

   @Override
public ReservationResponse createReservation(ReservationRequest request) {

    Reservation reservation = Reservation.builder()
            .checkInDate(request.getCheckInDate())
            .checkOutDate(request.getCheckOutDate())
            .numberOfGuests(request.getNumberOfGuests())
            .status(ReservationStatus.PENDING)
            .build();

    Reservation savedReservation = reservationRepository.save(reservation);

    return ReservationResponse.builder()
            .reservationId(savedReservation.getId())
            .userId(request.getUserId())
            .roomId(request.getRoomId())
            .checkInDate(savedReservation.getCheckInDate())
            .checkOutDate(savedReservation.getCheckOutDate())
            .numberOfGuests(savedReservation.getNumberOfGuests())
            .status(savedReservation.getStatus())
            .build();
}


    @Override
    public ReservationResponse getReservationById(Long id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        return ReservationResponse.builder()
                .reservationId(reservation.getId())
                .checkInDate(reservation.getCheckInDate())
                .checkOutDate(reservation.getCheckOutDate())
                .numberOfGuests(reservation.getNumberOfGuests())
                .status(reservation.getStatus())
                .build();
    }

    @Override
    public List<ReservationResponse> getAllReservations() {

        return reservationRepository.findAll().stream()
                .map(reservation -> ReservationResponse.builder()
                        .reservationId(reservation.getId())
                        .checkInDate(reservation.getCheckInDate())
                        .checkOutDate(reservation.getCheckOutDate())
                        .numberOfGuests(reservation.getNumberOfGuests())
                        .status(reservation.getStatus())
                        .build())
                .toList();
    }

    @Override
    public void cancelReservation(Long id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setStatus(ReservationStatus.CANCELLED);

        reservationRepository.save(reservation);
    }
}