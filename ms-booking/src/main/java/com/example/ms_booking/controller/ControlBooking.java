package com.example.ms_booking.controller;

import com.example.ms_booking.entity.EntityBooking;
import com.example.ms_booking.repository.RepoBooking;
import com.example.ms_booking.service.ServiceBooking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class ControlBooking {
    @Autowired
    ServiceBooking serviceBooking;
    @Autowired
    RepoBooking repoBooking;

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

    /**
     * Método para obtener una lista de reservas de un cliente
     * @param rut RUT del cliente
     * @return lista de reservas
     */
    public List<EntityBooking> getBookingsByUserRut(String rut) {
        List<EntityBooking> bookings = repoBooking.findByClientsRUTContains(rut);
        List<EntityBooking> filteredBookings = new ArrayList<>();

        if (bookings.isEmpty()) {
            System.out.println("No se encontraron reservas para el cliente con RUT: " + rut);
            return new ArrayList<>();
        } else {
            for (EntityBooking booking : bookings) {
                // Verificar si el RUT del cliente coincide con el RUT de la reserva
                List<String> clientsRUT = List.of(booking.getClientsRUT().split(","));
                if (clientsRUT.get(0).equals(rut)) {
                    filteredBookings.add(booking);
                }
            }
        }
        return filteredBookings;
    }

    /**
     * Método para obtener una lista de reservas por fecha
     * @param date fecha de la reserva
     * @return lista de horas de reserva
     */
    public List<LocalTime> getTimesByDate(LocalDate date){
        List<EntityBooking> bookings = repoBooking.findByBookingDate(date);
        List<LocalTime> times = new ArrayList<>();
        for (EntityBooking booking : bookings) {
            times.add(booking.getBookingTime());
        }
        return times;
    }

    /**
     * Método para obtener una lista de reservas por fecha final
     * @param date fecha de la reserva
     * @return lista de horas de reserva
     */
    public List<LocalTime> getTimesEndByDate(LocalDate date){
        List<EntityBooking> bookings = repoBooking.findByBookingDate(date);
        List<LocalTime> times = new ArrayList<>();
        for (EntityBooking booking : bookings) {
            times.add(booking.getBookingTimeEnd());
        }
        return times;
    }

    /**
     * Método para obtener una lista de reservas confirmadas
     * @return lista de reservas confirmadas
     */
    public List<EntityBooking> getConfirmedBookings() {
        return repoBooking.findByBookingStatusContains("confirmada");
    }

    //----------------------------------------------------------------
    // --- Método para obtener reservas para el rack semanal ---
    //----------------------------------------------------------------

    /**
     * Método para obtener una lista de reservas confirmadas por fecha (Mes/Año)
     * @param status Estado de la reserva
     * @param month Mes de la reserva
     * @param year Año de la reserva
     * @return lista de reservas
     */
    @GetMapping("/findByBookingStatusMonthYear/{status}/{month}/{year}")
    public List<EntityBooking> findByStatusAndMonthAndYear(@PathVariable String status, @PathVariable String month, @PathVariable String year){
        return serviceBooking.findByStatusAndMonthAndYear(status, month, year);
    }

    @GetMapping("/findByBookingDate/{bookingDate}")
    public List<EntityBooking> findByBookingDate(@PathVariable LocalDate bookingDate){
        return serviceBooking.findByBookingDate(bookingDate);
    }

    @GetMapping("/findByClientsRUTContains/{rut}")
    public List<EntityBooking> findByClientsRUTContains(@PathVariable String rut){
        return serviceBooking.findByClientsRUTContains(rut);
    }


    //----------------------------------------------------------------
    // --- Método para obtener una lista de reservas de un cliente ---
    //----------------------------------------------------------------

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
