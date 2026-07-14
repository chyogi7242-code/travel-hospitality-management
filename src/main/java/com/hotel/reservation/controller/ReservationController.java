package com.hotel.reservation.controller;

import com.hotel.reservation.dto.ReservationRequest;
import com.hotel.reservation.dto.ReservationResponse;
import com.hotel.reservation.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    // Get Reservation By ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                reservationService.getReservationById(id)
        );
    }


    // Get All Reservations
    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAllReservations() {

        return ResponseEntity.ok(
                reservationService.getAllReservations()
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

        reservationService.updateReservationStatus(id, status);

        return ResponseEntity.ok(
                "Reservation status updated successfully"
        );
    }
}