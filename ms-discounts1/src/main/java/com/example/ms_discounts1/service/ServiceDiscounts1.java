package com.example.ms_discounts1.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceDiscounts1 {

    public int discountsForNumOfPeople(Integer numOfPeople, int basePrice) {
        int discount;
        discount = (numOfPeople == 1 || numOfPeople == 2) ? 0
                : (3 <= numOfPeople && numOfPeople <= 5) ? 10
                : (6 <= numOfPeople && numOfPeople <= 10) ? 20
                : (11 <= numOfPeople && numOfPeople <= 15) ? 30
                : 0;
        int discountedPrice = basePrice - ((basePrice * discount) / 100);
        System.out.println("discount1: " + discount);
        System.out.print("Total price after discount: " + (basePrice - (basePrice * discount / 100)));
        return discountedPrice;
    }
}
