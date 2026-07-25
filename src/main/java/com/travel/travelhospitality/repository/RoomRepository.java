package com.travel.travelhospitality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.travelhospitality.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    // Search Rooms By Availability
    List<Room> findByAvailable(boolean available);

    // Search Rooms By Price Range
    List<Room> findByPriceBetween(double min, double max);
}