package com.travel.travelhospitality.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.travelhospitality.entity.Room;
import com.travel.travelhospitality.repository.RoomRepository;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Add Room
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    // Get All Rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Get Room By Id
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    // Update Room
    public Room updateRoom(Long id, Room room) {
        Room existingRoom = roomRepository.findById(id).orElse(null);

        if (existingRoom != null) {
            existingRoom.setRoomNumber(room.getRoomNumber());
            existingRoom.setRoomType(room.getRoomType());
            existingRoom.setPrice(room.getPrice());
            existingRoom.setCapacity(room.getCapacity());
            existingRoom.setAvailable(room.isAvailable());
            existingRoom.setHotel(room.getHotel());

            return roomRepository.save(existingRoom);
        }

        return null;
    }

    // Delete Room
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}