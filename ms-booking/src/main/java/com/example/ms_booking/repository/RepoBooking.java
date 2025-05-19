package com.example.ms_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoBooking extends JpaRepository<RepoBooking, Long> {
}
