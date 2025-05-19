package com.example.ms_booking.service;

import com.example.ms_booking.entity.Booking;
import com.example.ms_booking.repository.RepoBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceBooking {
    @Autowired
    private RepoBooking repoBooking;
    @Autowired
    RestTemplate restTemplate;

    public Booking saveBooking(Booking booking) {
        // Establecer tarifa base y duración de la reserva
        Integer lapsOrMaxTimeAllowed = booking.getLapsOrMaxTimeAllowed();
        Integer basePrice = getBasePrice(lapsOrMaxTimeAllowed);
        booking.setBasePrice(String.valueOf(basePrice));
        Integer duration = getDuration(lapsOrMaxTimeAllowed);
        booking.setBookingTimeEnd(booking.getBookingTime().plusMinutes(duration));

        //

        return booking;
    }

    /**
     * Método para obtener el precio base de acuerdo a la cantidad de vueltas o tiempo máximo permitido
     * @param lapsOrMaxTimeAllowed vueltas o tiempo máximo permitido
     * @return ResponseEntity<Integer>
     */
    public Integer getBasePrice(Integer lapsOrMaxTimeAllowed) {
        return restTemplate.getForObject("http://ms-rates/rates/basePrice/" + lapsOrMaxTimeAllowed, Integer.class);
    }

    /**
     * Método para obtener la duración de acuerdo a la cantidad de vueltas o tiempo máximo permitido
     * @param lapsOrMaxTimeAllowed vueltas o tiempo máximo permitido
     * @return ResponseEntity<Integer>
     */
    public Integer getDuration(Integer lapsOrMaxTimeAllowed) {
        return restTemplate.getForObject("http://ms-rates/rates/duration/" + lapsOrMaxTimeAllowed, Integer.class);
    }

}
