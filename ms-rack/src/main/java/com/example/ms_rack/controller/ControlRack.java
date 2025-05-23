package com.example.ms_rack.controller;

import com.example.ms_rack.entity.EntityRack;
import com.example.ms_rack.service.ServiceRack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/rack")
public class ControlRack {
    @Autowired
    ServiceRack serviceRack;

    @PostMapping("/save/{id}/{bookingDate}/{bookingTime}/{bookingTimeEnd}/{bookingStatus}/{clientName}")
    public void saveRack(@PathVariable Long id,
                         @PathVariable LocalDate bookingDate,
                         @PathVariable LocalTime bookingTime,
                         @PathVariable LocalTime bookingTimeEnd,
                         @PathVariable String bookingStatus,
                         @PathVariable String clientName) {
        serviceRack.saveRack(id, bookingDate, bookingTime, bookingTimeEnd, bookingStatus, clientName);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRack(@PathVariable Long id) {
        serviceRack.deleteRack(id);
    }

    @GetMapping("/getBookingsForRack/{month}/{year}")
    public List<EntityRack> getBookingsForRack(String month, String year){
        return serviceRack.getBookingsForRack(month, year);
    }
}

