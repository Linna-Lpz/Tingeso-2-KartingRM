package com.example.ms_booking.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EntityRackDTO {
    private Long id;
    private LocalDate bookingDate; // DD-MM-YYYY
    private LocalTime bookingTime; // HH:MM
    private LocalTime bookingTimeEnd; // HH:MM Tiempo total duración reserva
    private String bookingStatus; // Estado de la reserva (confirmada)
    private String clientsNames; // clientes
}
