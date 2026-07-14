package com.travel.travelhospitality.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.travelhospitality.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}