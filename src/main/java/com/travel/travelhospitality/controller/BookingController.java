package com.travel.travelhospitality.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.travel.travelhospitality.entity.Booking;
import com.travel.travelhospitality.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Add Booking
    @PostMapping
    public Booking addBooking(@Valid @RequestBody Booking booking) {
        return bookingService.saveBooking(booking);
    }

    // Get All Bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // Get Booking By Id
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    // Update Booking
    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id,
                                 @Valid @RequestBody Booking booking) {
        return bookingService.updateBooking(id, booking);
    }

    // Delete Booking
    @DeleteMapping("/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "Booking deleted successfully!";
    }

    // Search Bookings By Customer Name
    @GetMapping("/customer/{customerName}")
    public List<Booking> getBookingsByCustomerName(
            @PathVariable String customerName) {
        return bookingService.getBookingsByCustomerName(customerName);
    }
}