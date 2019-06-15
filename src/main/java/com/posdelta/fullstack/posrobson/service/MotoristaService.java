package com.posdelta.fullstack.posrobson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.posdelta.fullstack.posrobson.model.Motorista;
import com.posdelta.fullstack.posrobson.repository.MotoristaRepository;

@Service
public class MotoristaService {
	
	@Autowired
	private MotoristaRepository repository;
	
	public Motorista incluir(Motorista motorista) {
		motorista.setId(null);
		return repository.save(motorista);
	}
	
	public Motorista alterar(Motorista motorista) {
		pesquisaPorId(motorista.getId());
		return repository.save(motorista);
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}
	
	public List<Motorista> listar(){
		return repository.findAll();
	}
	
	public Motorista pesquisaPorId(Long id) {
		return repository.findById(id).orElseThrow(()
				-> new EmptyResultDataAccessException(0));
	}

}
