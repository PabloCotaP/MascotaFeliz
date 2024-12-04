package com.pablodev.repository;

import com.pablodev.model.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
}
