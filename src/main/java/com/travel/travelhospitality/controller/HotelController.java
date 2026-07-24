package com.travel.travelhospitality.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travelhospitality.entity.Hotel;
import com.travel.travelhospitality.service.HotelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // Add Hotel
    @PostMapping
    public Hotel addHotel(@Valid @RequestBody Hotel hotel) {
        return hotelService.saveHotel(hotel);
    }

    // Get All Hotels
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    // Get Hotel By Id
    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    // Update Hotel
    @PutMapping("/{id}")
    public Hotel updateHotel(@PathVariable Long id,
                             @Valid @RequestBody Hotel hotel) {
        return hotelService.updateHotel(id, hotel);
    }

    // Delete Hotel
    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return "Hotel deleted successfully!";
    }

    // Search Hotels By City
    @GetMapping("/city/{city}")
    public List<Hotel> getHotelsByCity(@PathVariable String city) {
        return hotelService.getHotelsByCity(city);
    }
}