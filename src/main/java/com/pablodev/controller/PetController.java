package com.pablodev.controller;

import com.pablodev.model.Pet;
import com.pablodev.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    // Endpoint para obtener todas las mascotas
    // GET /api/pets
    @GetMapping
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // Endpoint para obtener una mascota por ID
    // GET /api/pets/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        return pet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint para crear una nueva mascota
    // POST /api/pets
    @PostMapping
    public Pet createPet(@RequestBody Pet pet) {
        return petRepository.save(pet);
    }

    // Endpoint para actualizar una mascota existente
    // PUT /api/pets/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet petDetails) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            Pet updatedPet = pet.get();
            updatedPet.setNombre(petDetails.getNombre());
            updatedPet.setEdad(petDetails.getEdad());
            updatedPet.setGenero(petDetails.getGenero());
            updatedPet.setTamano(petDetails.getTamano());
            updatedPet.setRaza(petDetails.getRaza());
            updatedPet.setDescripcion(petDetails.getDescripcion());
            updatedPet.setFechaIngreso(petDetails.getFechaIngreso());
            updatedPet.setEstadoSalud(petDetails.getEstadoSalud());
            updatedPet.setVacunado(petDetails.isVacunado());
            updatedPet.setEsterilizado(petDetails.isEsterilizado());
            updatedPet.setAdoptado(petDetails.isAdoptado());
            updatedPet.setTipo(petDetails.getTipo());
            return ResponseEntity.ok(petRepository.save(updatedPet));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para eliminar una mascota
    // DELETE /api/pets/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            petRepository.delete(pet.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}