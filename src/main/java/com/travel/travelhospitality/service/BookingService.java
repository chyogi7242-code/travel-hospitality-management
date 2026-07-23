package com.travel.travelhospitality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.travelhospitality.entity.Booking;
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
        return bookingRepository.findById(id).orElse(null);
    }

    // Update Booking
    public Booking updateBooking(Long id, Booking booking) {

        Booking existingBooking = bookingRepository.findById(id).orElse(null);

        if (existingBooking != null) {

            existingBooking.setCustomerName(booking.getCustomerName());
            existingBooking.setCheckInDate(booking.getCheckInDate());
            existingBooking.setCheckOutDate(booking.getCheckOutDate());
            existingBooking.setNumberOfGuests(booking.getNumberOfGuests());
            existingBooking.setRoom(booking.getRoom());

            return bookingRepository.save(existingBooking);
        }

        return null;
    }

    // Delete Booking
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}