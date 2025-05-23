package com.example.ms_booking.controller;

import com.example.ms_booking.entity.EntityBooking;
import com.example.ms_booking.service.ServiceBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class ControlBooking {
    @Autowired
    ServiceBooking serviceBooking;

    @PostMapping("/save")
    public ResponseEntity<EntityBooking> saveBooking(@RequestBody EntityBooking booking) {
        serviceBooking.saveBooking(booking);
        return ResponseEntity.ok(booking);
    }

    /**
     * Método para obtener una lista de reservas
     * @return Lista de reservas
     */
    @GetMapping("/getBookings")
    public ResponseEntity<List<EntityBooking>> getBookings() {
        List<EntityBooking> bookings = serviceBooking.getBookings();
        return ResponseEntity.ok(bookings);
    }

    /**
     * Método para confirmar o cancelar una reserva
     * @param bookingId ID de la reserva
     *
     */
    @PostMapping("/confirm/{bookingId}")
    public ResponseEntity<String> confirmBooking(@PathVariable Long bookingId) {
        serviceBooking.confirmBooking(bookingId);
        return ResponseEntity.ok("Reserva confirmada");
    }


    /**
     * Método para cancelar una reserva
     * @param bookingId ID de la reserva
     */
    @PostMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        serviceBooking.cancelBooking(bookingId);
        return ResponseEntity.ok("Reserva cancelada");
    }

    @GetMapping("/findByStatusDayTimeAllowed/{status}/{month}/{maxTimeAllowed}")
    public ResponseEntity<List<EntityBooking>> findByStatusAndDayAndLapsOrMaxTime(@PathVariable String status, @PathVariable String month, @PathVariable Integer maxTimeAllowed) {
        List<EntityBooking> bookings = serviceBooking.findByStatusAndDayAndLapsOrMaxTime(status, month, maxTimeAllowed);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/findByStatusDayPeople1/{status}/{month}/{numOfPeople}")
    public ResponseEntity<List<EntityBooking>> findByStatusAndDayAndNumOfPeople1or2(@PathVariable String status, @PathVariable String month, @PathVariable Integer numOfPeople) {
        List<EntityBooking> bookings = serviceBooking.findByStatusAndDayAndNumOfPeople1or2(status, month, numOfPeople);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/findByStatusDayPeople2/{status}/{month}/{numOfPeople}")
    public ResponseEntity<List<EntityBooking>> findByStatusAndDayAndNumOfPeople3to5(@PathVariable String status, @PathVariable String month, @PathVariable Integer numOfPeople) {
        List<EntityBooking> bookings = serviceBooking.findByStatusAndDayAndNumOfPeople3to5(status, month, numOfPeople);
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/findByStatusDayPeople3/{status}/{month}/{numOfPeople}")
    public ResponseEntity<List<EntityBooking>> findByStatusAndDayAndNumOfPeople6to10(@PathVariable String status, @PathVariable String month, @PathVariable Integer numOfPeople) {
        List<EntityBooking> bookings = serviceBooking.findByStatusAndDayAndNumOfPeople6to10(status, month, numOfPeople);
        return ResponseEntity.ok(bookings);
    }
    @GetMapping("/findByStatusDayPeople4/{status}/{month}/{numOfPeople}")
    public ResponseEntity<List<EntityBooking>> findByStatusAndDayAndNumOfPeople11to15(@PathVariable String status, @PathVariable String month, @PathVariable Integer numOfPeople) {
        List<EntityBooking> bookings = serviceBooking.findByStatusAndDayAndNumOfPeople11to15(status, month, numOfPeople);
        return ResponseEntity.ok(bookings);
    }
}
