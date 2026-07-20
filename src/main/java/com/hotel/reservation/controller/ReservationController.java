package com.hotel.reservation.controller;

import com.hotel.reservation.dto.ReservationRequest;
import com.hotel.reservation.dto.ReservationResponse;
import com.hotel.reservation.service.ReservationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/reservations")
@RequiredArgsConstructor
public class ReservationController {


    private final ReservationService reservationService;




    // Create Reservation
    @PostMapping
    public ResponseEntity<ReservationResponse> createReservation(
            @Valid @RequestBody ReservationRequest request) {


        ReservationResponse response =
                reservationService.createReservation(request);


        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );

    }






    // Get Reservation By ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(
            @PathVariable Long id) {


        return ResponseEntity.ok(

                reservationService.getReservationById(id)

        );

    }







    // Get All Reservations Pagination + Sorting
    @GetMapping
    public ResponseEntity<Page<ReservationResponse>> getReservations(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy) {



        return ResponseEntity.ok(

                reservationService.getReservations(
                        page,
                        size,
                        sortBy
                )

        );

    }







    // Get Logged User Reservations
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<ReservationResponse>> getUserReservations(

            @PathVariable Long userId,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy) {



        return ResponseEntity.ok(

                reservationService.getUserReservations(
                        userId,
                        page,
                        size,
                        sortBy
                )

        );

    }







    // Cancel Reservation
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelReservation(
            @PathVariable Long id) {


        reservationService.cancelReservation(id);


        return ResponseEntity.ok(
                "Reservation cancelled successfully"
        );

    }








    // Update Reservation Status
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateReservationStatus(

            @PathVariable Long id,

            @RequestParam String status) {



        reservationService.updateReservationStatus(
                id,
                status
        );



        return ResponseEntity.ok(
                "Reservation status updated successfully"
        );

    }


}