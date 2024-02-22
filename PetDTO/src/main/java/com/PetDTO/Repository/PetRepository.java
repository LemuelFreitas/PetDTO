package com.PetDTO.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PetDTO.Entities.PetEntities;

public interface PetRepository extends JpaRepository<PetEntities, Long>{

}