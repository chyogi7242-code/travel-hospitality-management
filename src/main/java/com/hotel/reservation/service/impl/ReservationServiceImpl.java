package com.hotel.reservation.service.impl;

import com.hotel.reservation.dto.ReservationRequest;
import com.hotel.reservation.dto.ReservationResponse;
import com.hotel.reservation.entity.Reservation;
import com.hotel.reservation.entity.ReservationStatus;
import com.hotel.reservation.entity.User;
import com.hotel.reservation.exception.ReservationNotFoundException;
import com.hotel.reservation.repository.ReservationRepository;
import com.hotel.reservation.repository.UserRepository;
import com.hotel.reservation.service.ReservationService;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {


    private final ReservationRepository reservationRepository;

    private final UserRepository userRepository;



    @Override
    public ReservationResponse createReservation(
            ReservationRequest request) {


        User user = userRepository.findById(request.getUserId())

                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with id: "
                                        + request.getUserId()
                        )
                );



        Reservation reservation = Reservation.builder()

                .user(user)

                .checkInDate(request.getCheckInDate())

                .checkOutDate(request.getCheckOutDate())

                .numberOfGuests(request.getNumberOfGuests())

                .status(ReservationStatus.PENDING)

                .build();



        Reservation saved = reservationRepository.save(reservation);



        return mapToResponse(saved);

    }






    @Override
    public ReservationResponse getReservationById(Long id) {


        Reservation reservation = reservationRepository.findById(id)

                .orElseThrow(() ->
                        new ReservationNotFoundException(
                                "Reservation not found with id : " + id
                        )
                );



        return mapToResponse(reservation);

    }






    @Override
    public List<ReservationResponse> getAllReservations() {


        return reservationRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .toList();

    }







    @Override
    public Page<ReservationResponse> getReservations(
            int page,
            int size,
            String sortBy) {


        Pageable pageable = PageRequest.of(

                page,

                size,

                Sort.by(sortBy).ascending()

        );



        return reservationRepository

                .findAll(pageable)

                .map(this::mapToResponse);

    }







    @Override
    public Page<ReservationResponse> getUserReservations(
            Long userId,
            int page,
            int size,
            String sortBy) {


        Pageable pageable = PageRequest.of(

                page,

                size,

                Sort.by(sortBy).ascending()

        );



        return reservationRepository

                .findByUserId(userId, pageable)

                .map(this::mapToResponse);

    }







    @Override
    public void cancelReservation(Long id) {


        Reservation reservation = reservationRepository.findById(id)

                .orElseThrow(() ->
                        new ReservationNotFoundException(
                                "Reservation not found with id : " + id
                        )
                );



        reservation.setStatus(
                ReservationStatus.CANCELLED
        );



        reservationRepository.save(reservation);

    }








    @Override
    public void updateReservationStatus(
            Long id,
            String status) {


        Reservation reservation = reservationRepository.findById(id)

                .orElseThrow(() ->
                        new ReservationNotFoundException(
                                "Reservation not found with id : " + id
                        )
                );



        try {


            reservation.setStatus(

                    ReservationStatus.valueOf(
                            status.toUpperCase()
                    )

            );


        } catch (IllegalArgumentException e) {


            throw new RuntimeException(
                    "Invalid reservation status: " + status
            );

        }



        reservationRepository.save(reservation);

    }








    private ReservationResponse mapToResponse(
            Reservation reservation) {


        ReservationResponse response =
                new ReservationResponse();



        response.setReservationId(
                reservation.getId()
        );



        if(reservation.getUser() != null) {

            response.setUserId(
                    reservation.getUser().getId()
            );

        }



        response.setCheckInDate(
                reservation.getCheckInDate()
        );


        response.setCheckOutDate(
                reservation.getCheckOutDate()
        );


        response.setNumberOfGuests(
                reservation.getNumberOfGuests()
        );


        response.setStatus(
                reservation.getStatus()
        );



        return response;

    }


}