package com.travel.travelhospitality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.travel.travelhospitality.entity.Hotel;
import com.travel.travelhospitality.exception.ResourceNotFoundException;
import com.travel.travelhospitality.repository.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    // Add Hotel
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // Get All Hotels
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // Get Hotel By Id
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with id: " + id));
    }

    // Update Hotel
    public Hotel updateHotel(Long id, Hotel hotel) {

        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with id: " + id));

        existingHotel.setHotelName(hotel.getHotelName());
        existingHotel.setCity(hotel.getCity());
        existingHotel.setAddress(hotel.getAddress());
        existingHotel.setDescription(hotel.getDescription());
        existingHotel.setRating(hotel.getRating());

        return hotelRepository.save(existingHotel);
    }

    // Delete Hotel
    public void deleteHotel(Long id) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Hotel not found with id: " + id));

        hotelRepository.delete(hotel);
    }

    // Search Hotels By City
    public List<Hotel> getHotelsByCity(String city) {
        return hotelRepository.findByCity(city);
    }

    // Pagination
    public Page<Hotel> getHotelsByPage(int page, int size) {
        return hotelRepository.findAll(PageRequest.of(page, size));
    }

    // Sorting
    public List<Hotel> getHotelsSortedBy(String field) {
        return hotelRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }
}