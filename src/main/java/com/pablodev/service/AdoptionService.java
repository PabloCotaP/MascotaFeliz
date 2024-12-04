package com.pablodev.service;

import com.pablodev.model.Adoption;
import com.pablodev.repository.AdoptionRepository;
import com.pablodev.repository.PetRepository;
import com.pablodev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionService {
    @Autowired
    private AdoptionRepository adoptionRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    public Adoption createAdoption(Adoption adoption) {
        petRepository.findById(adoption.getPetId())
                .orElseThrow(() -> new RuntimeException("Error al encontrar la mascota"));
        userRepository.findById(adoption.getUserId())
                .orElseThrow(() -> new RuntimeException("Error al encontrar al usuario"));

        return adoptionRepository.save(adoption);
    }

    public List<Adoption> getAllAdoptions() {
        return adoptionRepository.findAll();
    }

    public Adoption getAdoptionById(Long id) {
        return adoptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error al encontrar la adopción"));
    }

    public Adoption updateAdoption(Long id, Adoption adoptionDetails) {
        Adoption adoption = adoptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error al encontrar la adopción"));

        petRepository.findById(adoptionDetails.getPetId())
                .orElseThrow(() -> new RuntimeException("Error al encontrar la mascota"));
        userRepository.findById(adoptionDetails.getUserId())
                .orElseThrow(() -> new RuntimeException("Error al encontrar al usuario"));


        adoption.setPetId(adoptionDetails.getPetId());
        adoption.setUserId(adoptionDetails.getUserId());
        adoption.setFechaAdopcion(adoptionDetails.getFechaAdopcion());
        adoption.setEstado(adoptionDetails.getEstado());
        adoption.setMotivoAdopcion(adoptionDetails.getMotivoAdopcion());

        return adoptionRepository.save(adoption);
    }

    public void deleteAdoption(Long id) {
        Adoption adoption = adoptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error al encontrar la adopción"));

        adoptionRepository.delete(adoption);
    }
}
