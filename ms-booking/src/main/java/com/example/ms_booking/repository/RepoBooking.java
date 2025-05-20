package com.example.ms_booking.repository;

import com.example.ms_booking.entity.EntityBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoBooking extends JpaRepository<EntityBooking, Long> {
}
