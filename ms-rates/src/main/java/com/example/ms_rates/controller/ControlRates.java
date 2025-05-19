package com.example.ms_rates.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rates")
public class ControlRates {
    @Autowired
    ServiceRates serviceRates;

    /**
     * Método para obtener el precio base de acuerdo a la cantidad de vueltas o tiempo máximo permitido
     * @param lapsOrMaxTimeAllowed
     * @return ResponseEntity<Integer>
     */
    @GetMapping("/basePrice/{lapsOrMaxTimeAllowed}")
    public ResponseEntity<Integer> getBasePrice(@PathVariable Integer lapsOrMaxTimeAllowed) {
        int basePrice = serviceRates.calculatePrice(lapsOrMaxTimeAllowed);
        return ResponseEntity.ok(basePrice);
    }

    /**
     * Método para obtener la duración de acuerdo a la cantidad de vueltas o tiempo máximo permitido
     * @param lapsOrMaxTimeAllowed
     * @return ResponseEntity<Integer>
     */
    @GetMapping("/duration/{lapsOrMaxTimeAllowed}")
    public ResponseEntity<Integer> getDuration(@PathVariable Integer lapsOrMaxTimeAllowed) {
        int duration = serviceRates.calculateDuration(lapsOrMaxTimeAllowed);
        return ResponseEntity.ok(duration);
    }

}
