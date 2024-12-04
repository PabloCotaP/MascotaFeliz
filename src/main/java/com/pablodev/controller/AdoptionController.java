package com.pablodev.controller;

import com.pablodev.model.Adoption;
import com.pablodev.service.AdoptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adoption")
public class AdoptionController {
    @Autowired
    private AdoptionService adoptionService;

    @PostMapping
    public Adoption createAdoption(@RequestBody Adoption adoption) {
        return adoptionService.createAdoption(adoption);
    }

    @GetMapping
    public List<Adoption> getAllAdoptions() {
        return adoptionService.getAllAdoptions();
    }

    @GetMapping("/{id}")
    public Adoption getAdoptionById(@PathVariable Long id) {
        return adoptionService.getAdoptionById(id);
    }

    @PutMapping("/{id}")
    public Adoption updateAdoption(@PathVariable Long id, @Valid @RequestBody Adoption adoptionDetails) {
        return adoptionService.updateAdoption(id, adoptionDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdoption(@PathVariable Long id) {
        adoptionService.deleteAdoption(id);
        return ResponseEntity.ok().build();
    }
}
