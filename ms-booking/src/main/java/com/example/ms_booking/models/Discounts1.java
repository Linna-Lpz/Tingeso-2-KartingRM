package com.example.ms_booking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discounts1 {
    private String clientsRUT;
    private Integer numOfPeople;
    private String basePrice;
    private String discounts;
}
