package com.example.ms_booking.controller;

import com.example.ms_booking.entity.Booking;
import com.example.ms_booking.service.ServiceBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class ControlBooking {
    @Autowired
    private ServiceBooking serviceBooking;

    @PostMapping("/save")
    public ResponseEntity<Booking> saveBooking(@RequestBody Booking booking) {
        serviceBooking.saveBooking(booking);
        return ResponseEntity.ok(booking);
    }
}
