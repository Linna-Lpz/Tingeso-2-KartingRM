package com.example.ms_discounts2.controller;

import com.example.ms_discounts2.service.ServiceDiscounts2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discounts2")
public class ControlDiscounts2 {
    @Autowired
    ServiceDiscounts2 serviceDiscounts2;

    public int discountForVisistPerMonth(Integer visitsPerMonth, int basePrice){
        return serviceDiscounts2.discountForVisistPerMonth(visitsPerMonth, basePrice);
    }
}
