package com.example.ms_discounts2.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceDiscounts2 {
    public int discountForVisistPerMonth(Integer visitsPerMonth, int basePrice) {
        int discount;
        discount = (2 <= visitsPerMonth && visitsPerMonth <= 4) ? 10
                : (5 == visitsPerMonth || visitsPerMonth == 6) ? 20
                : (visitsPerMonth >= 7) ? 30
                : 0;
        int discountedPrice = basePrice - ((basePrice * discount) / 100);
        System.out.println("discount2: " + discount);
        System.out.print("Total price after discount: " + (basePrice - (basePrice * discount / 100)));
        return discountedPrice;
    }
}
