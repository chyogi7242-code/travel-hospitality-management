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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.travel.travelhospitality.entity.Room;
import com.travel.travelhospitality.service.RoomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Add Room
    @PostMapping
    public Room addRoom(@Valid @RequestBody Room room) {
        return roomService.saveRoom(room);
    }

    // Get All Rooms
    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    // Get Room By Id
    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    // Update Room
    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id,
                           @Valid @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    // Delete Room
    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "Room deleted successfully!";
    }

    // Search Rooms By Availability
    @GetMapping("/available/{available}")
    public List<Room> getRoomsByAvailability(@PathVariable boolean available) {
        return roomService.getRoomsByAvailability(available);
    }

    // Search Rooms By Price Range
    @GetMapping("/price")
    public List<Room> getRoomsByPriceRange(
            @RequestParam double min,
            @RequestParam double max) {

        return roomService.getRoomsByPriceRange(min, max);
    }
}