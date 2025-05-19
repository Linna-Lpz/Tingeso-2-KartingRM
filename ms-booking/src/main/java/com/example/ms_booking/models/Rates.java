package com.example.ms_booking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rates {
    private Integer lapsOrMaxTimeAllowed;
    private Integer basePrice;
    private Integer duration;
}
