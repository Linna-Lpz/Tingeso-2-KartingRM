package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class EntityClient {
    @Id
    private String clientRUT; // 12345678-9
    private String clientName; // Nombre Apellido
    private String clientEmail;
    private String clientBirthday; // DD-MM
    private Integer visistsPerMonth;
}
