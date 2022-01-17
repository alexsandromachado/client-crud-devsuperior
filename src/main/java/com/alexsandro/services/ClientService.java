package com.alexsandro.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alexsandro.dto.ClientDTO;
import com.alexsandro.entities.Client;
import com.alexsandro.repositories.ClientRepository;
import com.alexsandro.services.exceptions.DatabaseException;
import com.alexsandro.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		return	list.map(i -> new ClientDTO(i));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> obj = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found."));
		return new ClientDTO(entity);
	}
	
	@Transactional
	public ClientDTO save(ClientDTO dto) {
		Client entity = new Client();
		copyToEntity(dto, entity);
		return new ClientDTO(repository.save(entity));
	}
	
	@Transactional
	public ClientDTO update(ClientDTO dto, Long id) {
		try {
			Client entity = repository.getById(id);
			copyToEntity(dto, entity);
			dto = new ClientDTO(repository.save(entity));
			return dto;
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found: "+ id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found "+id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private void copyToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setChildren(dto.getChildren());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
	}

}
