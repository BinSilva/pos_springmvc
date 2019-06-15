package com.posdelta.fullstack.posrobson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.posdelta.fullstack.posrobson.model.Carro;
import com.posdelta.fullstack.posrobson.repository.CarroRepository;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repository;
	
	public Carro incluir(Carro carro) {
		carro.setId(null);
		return repository.save(carro);
	}
	
	public Carro alterar(Carro carro) {
		pesquisaPorId(carro.getId());
		return repository.save(carro);
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}
	
	public List<Carro> listar(){
		return repository.findAll();
	}
	
	public Carro pesquisaPorId(Long id) {
		return repository.findById(id).orElseThrow(()
				-> new EmptyResultDataAccessException(0));
	}

}
