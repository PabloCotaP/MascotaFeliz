package com.pablodev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombres", length = 30, nullable = false)
    private String nombres;

    @Column(name = "apellidos", length = 30, nullable = false)
    private String apellidos;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;

    @Column(name = "role", length = 10, nullable = false)
    private String role = "USER";

    @Column(name = "active", nullable = false)
    private boolean active = true;

    public User() {}

    public User(String nombres, String apellidos, String email, String password, String telefono) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
    }
}
