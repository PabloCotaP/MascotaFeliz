package com.pablodev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "mascotas")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "edad", length = 20, nullable = false)
    private String edad;

    @Column(name = "genero", length = 10, nullable = false)
    private String genero;

    @Column(name = "tamaño", length = 20, nullable = false)
    private String tamano;

    @Column(name = "raza", length = 50, nullable = false)
    private String raza;

    @Column(name = "descripcion", length = 255, nullable = false)
    private String descripcion;

    @Column(name = "fecha_ingreso", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    @Column(name = "estado_salud", length = 50, nullable = false)
    private String estadoSalud;

    @Column(name = "vacunado", nullable = false)
    private boolean vacunado;

    @Column(name = "esterilizado", nullable = false)
    private boolean esterilizado;

    @Column(name = "adoptado", nullable = false)
    private boolean adoptado = false;

    @Column(name = "tipo", length = 30, nullable = false)
    private String tipo;

    public Pet() {}

    public Pet(String nombre, String edad, String genero, String tamano, String raza, String descripcion, Date fechaIngreso, String estadoSalud, boolean vacunado, boolean esterilizado, boolean adoptado, String tipo) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.tamano = tamano;
        this.raza = raza;
        this.descripcion = descripcion;
        this.fechaIngreso = fechaIngreso;
        this.estadoSalud = estadoSalud;
        this.vacunado = vacunado;
        this.esterilizado = esterilizado;
        this.adoptado = adoptado;
        this.tipo = tipo;
    }
}