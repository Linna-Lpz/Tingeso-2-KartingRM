package com.example.ms_reports.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report")
public class EntityReport {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_date")
    private LocalDate bookingDate; // DD-MM-YYYY
    @Column(name = "booking_time_end")
    private LocalTime bookingTimeEnd; // HH:MM Tiempo total duración reserva

    private String bookingStatus; // Estado de la reserva (confirmada, sin confirmar, cancelada)
    private Integer lapsOrMaxTimeAllowed;
    private Integer numOfPeople;
    private String basePrice; // Tarifa base
}
