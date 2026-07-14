package com.hotel.reservation.service;

import com.hotel.reservation.dto.ReservationRequest;
import com.hotel.reservation.dto.ReservationResponse;
import com.hotel.reservation.entity.Reservation;
import com.hotel.reservation.entity.ReservationStatus;
import com.hotel.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {


    private final ReservationRepository reservationRepository;


    @Override
    public ReservationResponse createReservation(ReservationRequest request) {

        // Reservation creation logic will be implemented in next step

        return null;
    }


    @Override
    public ReservationResponse getReservationById(Long id) {

        // Get reservation logic will be implemented in next step

        return null;
    }


    @Override
    public List<ReservationResponse> getAllReservations() {

        // Get all reservations logic will be implemented in next step

        return null;
    }


    @Override
    public void cancelReservation(Long id) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Reservation not found"));

        reservation.setStatus(ReservationStatus.CANCELLED);

        reservationRepository.save(reservation);
    }


    @Override
    public void updateReservationStatus(Long id, String status) {

        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Reservation not found"));


        try {

            ReservationStatus reservationStatus =
                    ReservationStatus.valueOf(status.toUpperCase());

            reservation.setStatus(reservationStatus);

        } catch (IllegalArgumentException e) {

            throw new RuntimeException(
                    "Invalid reservation status: " + status
            );
        }


        reservationRepository.save(reservation);
    }

}