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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet pet;

    @Column(name = "fecha_adopcion", nullable = false)
    private LocalDate adoptionDate;

    @Column(name = "estado", length = 50, nullable = false)
    private String estado;

    @Column(name = "motivo", length = 255, nullable = false)
    private String motivo;

    public Adoption() {}

    public Adoption(User user, Pet pet, LocalDate adoptionDate, String estado, String motivo) {
        this.user = user;
        this.pet = pet;
        this.adoptionDate = adoptionDate;
        this.estado = estado;
        this.motivo = motivo;
    }
}
