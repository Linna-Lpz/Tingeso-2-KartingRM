package com.example.ms_rates.service;

import com.example.ms_rates.repository.RepoRates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceRates {

    @Autowired
    RepoRates repoRates;

    public int calculatePrice(Integer lapsOrMaxTimeAllowed){
        int basePrice = repoRates.findBasePriceByLapsOrMaxTime(lapsOrMaxTimeAllowed);
        /**int basePrice;
        basePrice = (lapsOrMaxTimeAllowed == 10)? 15000
                : (lapsOrMaxTimeAllowed == 15) ? 20000
                : (lapsOrMaxTimeAllowed == 20) ? 25000
                : 0;*/
        return basePrice;
    }

    public int calculateDuration(Integer lapsOrMaxTimeAllowed){
        int duration = repoRates.findDurationByLapsOrMaxTime(lapsOrMaxTimeAllowed);
        /**int duration;
        duration = (lapsOrMaxTimeAllowed == 10)? 30
                : (lapsOrMaxTimeAllowed == 15) ? 35
                : (lapsOrMaxTimeAllowed == 20) ? 40
                : 0;*/
        return duration;
    }
}
