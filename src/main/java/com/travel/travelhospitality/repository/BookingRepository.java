package com.travel.travelhospitality.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.travelhospitality.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Search Booking By Customer Name
    List<Booking> findByCustomerName(String customerName);

}