package com.travel.travelhospitality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.travelhospitality.entity.Booking;
import com.travel.travelhospitality.exception.ResourceNotFoundException;
import com.travel.travelhospitality.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Add Booking
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Get All Bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get Booking By Id
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found with id: " + id));
    }

    // Update Booking
    public Booking updateBooking(Long id, Booking booking) {

        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found with id: " + id));

        existingBooking.setCustomerName(booking.getCustomerName());
        existingBooking.setCheckInDate(booking.getCheckInDate());
        existingBooking.setCheckOutDate(booking.getCheckOutDate());
        existingBooking.setNumberOfGuests(booking.getNumberOfGuests());
        existingBooking.setRoom(booking.getRoom());

        return bookingRepository.save(existingBooking);
    }

    // Delete Booking
    public void deleteBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found with id: " + id));

        bookingRepository.delete(booking);
    }
}