package com.example.ms_special_rates.controller;

import com.example.ms_special_rates.service.ServiceSpecialRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/special-rates")
public class ControlSpecialRates {
    @Autowired
    ServiceSpecialRates serviceSpecialRates;

    @GetMapping("/discount/{clientBirthday}/{bookingDayMonth}")
    public int discountForBirthday(@PathVariable String clientBirthday, @PathVariable String bookingDayMonth){
        return serviceSpecialRates.discountForBirthday(clientBirthday, bookingDayMonth);
    }
}
