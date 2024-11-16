package com.pablodev.controller;

import com.pablodev.model.User;
import com.pablodev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint para registrar un usuario
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar el usuario");
        }
    }

    // Endpoint de prueba para comprobar la conexion entre back y front
    @GetMapping(value = "/test")
    public ResponseEntity<?> testConnection() {
        try {
            System.out.println("Conexión exitosa con el backend");
            return ResponseEntity.ok("Conexión exitosa con el backend");
        } catch (Exception e) {
            System.out.println("Error en la conexión con el backend");
            return ResponseEntity.badRequest().body("Error en la conexión con el backend");
        }
    }
}

