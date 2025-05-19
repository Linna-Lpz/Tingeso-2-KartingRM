package com.example.ms_booking.controller;

import com.example.ms_booking.service.ServiceBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class ControlBooking {
    @Autowired
    private ServiceBooking serviceBooking;
}
