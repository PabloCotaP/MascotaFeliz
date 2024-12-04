package com.pablodev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "adopciones")
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mascota_id", nullable = false)
    private Long petId;

    @Column(name = "usuario_id", nullable = false)
    private Long userId;

    @Column(name = "fecha_adopcion", nullable = false)
    private LocalDate fechaAdopcion;

    @Column(name = "estado", length = 50, nullable = false)
    private String estado;

    @Column(name = "motivo_adopcion", nullable = false)
    private String motivoAdopcion;

    public Adoption() {}

    public Adoption(Long user, Long pet, LocalDate adoptionDate, String estado, String motivo) {
        this.userId = user;
        this.petId = pet;
        this.fechaAdopcion = adoptionDate;
        this.estado = estado;
        this.motivoAdopcion = motivo;
    }
}