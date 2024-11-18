package com.pablodev.controller;

import com.pablodev.model.User;
import com.pablodev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    // Endpoint para loguear un usuario
    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            Map<String, Object> response = userService.loginUser(user);
            if (response != null && (boolean) response.get("authenticated")) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body("Credenciales incorrectas");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al loguear el usuario");
        }
    }

    // Endpoint para cerrar la sesion de un usuario
    @GetMapping(value = "/logout")
    public ResponseEntity<?> logoutUser() {
        try {
            userService.logoutUser();
            return ResponseEntity.ok("Sesión cerrada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al cerrar la sesión");
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

