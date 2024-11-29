package com.pablodev.controller;

import com.pablodev.repository.AdoptionRepository;
import com.pablodev.repository.UserRepository;
import com.pablodev.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AdoptionRepository adoptionRepository;

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("totalUsers", (long) userRepository.findByRole("USER").size());
        stats.put("totalAdmins", (long) userRepository.findByRole("ADMIN").size());
        stats.put("totalPets", petRepository.count());
        stats.put("adoptions", adoptionRepository.count());
        return ResponseEntity.ok(stats);
    }

}
