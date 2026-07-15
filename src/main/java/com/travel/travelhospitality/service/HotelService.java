package com.travel.travelhospitality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.travelhospitality.entity.Hotel;
import com.travel.travelhospitality.repository.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    // Save Hotel
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    // Get All Hotels
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // Get Hotel By Id
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElse(null);
    }

    // Delete Hotel
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    // Update Hotel
    public Hotel updateHotel(Long id, Hotel hotel) {
        Hotel existingHotel = hotelRepository.findById(id).orElse(null);

        if (existingHotel != null) {
            existingHotel.setHotelName(hotel.getHotelName());
            existingHotel.setCity(hotel.getCity());
            existingHotel.setAddress(hotel.getAddress());
            existingHotel.setDescription(hotel.getDescription());
            existingHotel.setRating(hotel.getRating());

            return hotelRepository.save(existingHotel);
        }

        return null;
    }
}