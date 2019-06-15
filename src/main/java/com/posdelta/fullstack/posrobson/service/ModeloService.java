package com.posdelta.fullstack.posrobson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.posdelta.fullstack.posrobson.model.Modelo;
import com.posdelta.fullstack.posrobson.repository.ModeloRepository;

@Service
public class ModeloService {
	
	@Autowired
	private ModeloRepository repository;
	
	public Modelo alterar(Modelo Modelo) {
		pesquisaPorId(Modelo.getId());
		return repository.save(Modelo);
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}
	
	public List<Modelo> listar(){
		return repository.findAll();
	}
	
	public Modelo pesquisaPorId(Long id) {
		return repository.findById(id).orElseThrow(()
				-> new EmptyResultDataAccessException(0));
	}

}
