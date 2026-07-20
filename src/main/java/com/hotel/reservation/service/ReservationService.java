package com.hotel.reservation.service;

import com.hotel.reservation.dto.ReservationRequest;
import com.hotel.reservation.dto.ReservationResponse;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ReservationService {


    ReservationResponse createReservation(
            ReservationRequest request
    );


    ReservationResponse getReservationById(
            Long id
    );


    List<ReservationResponse> getAllReservations();


    Page<ReservationResponse> getReservations(
            int page,
            int size,
            String sortBy
    );


    Page<ReservationResponse> getUserReservations(
            Long userId,
            int page,
            int size,
            String sortBy
    );


    void cancelReservation(
            Long id
    );


    void updateReservationStatus(
            Long id,
            String status
    );

}