package com.PetDTO.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PetDTO.DTO.PetDTO;
import com.PetDTO.Entities.PetEntities;
import com.PetDTO.Repository.PetRepository;

@Service
public class PetService {
	
	private final PetRepository petRepository;
	
	@Autowired
	public PetService(PetRepository petRepository) {
		this.petRepository = petRepository;
	}
	//Método modificando para utilizar o DTO
	public PetDTO salvar(PetDTO petDTO) {
		//convert DTO to entity
		PetEntities pet = new PetEntities(petDTO.nome(),petDTO.nascimento(),petDTO.cuidador());
		PetEntities salvarPet = petRepository.save(pet);
		return new PetDTO(salvarPet.getId(), salvarPet.getNome(), salvarPet.getNascimento(), salvarPet.getCuidador());
	}
	
	//métodod modificado para utilizar o DTO
	public PetDTO atualizar(Long id, PetDTO petDTO) {
		PetEntities existePet = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet não encontrado"));
		
		existePet.setNome(petDTO.nome());
		existePet.setNascimento(petDTO.nascimento());
		existePet.setCuidador(petDTO.cuidador());
		
		PetEntities updatePet = petRepository.save(existePet);
		return new PetDTO(updatePet.getId(), updatePet.getNome(), updatePet.getNascimento(), updatePet.getCuidador());
	}
	public boolean deletar(Long id) {
		Optional <PetEntities> existePet = petRepository.findById(id);
		if (existePet.isPresent() ) {
			petRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public List <PetEntities> buscarTodos() {
		return petRepository.findAll();
	}
	public PetEntities buscarPorId(Long id) {
		Optional <PetEntities> pet = petRepository.findById(id);
		return pet.orElse(null);
	}
}

