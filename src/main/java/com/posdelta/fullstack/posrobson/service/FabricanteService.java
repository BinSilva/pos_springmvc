package com.posdelta.fullstack.posrobson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.posdelta.fullstack.posrobson.model.Fabricante;
import com.posdelta.fullstack.posrobson.repository.FabricanteRepository;

@Service
public class FabricanteService {
	
	@Autowired
	private FabricanteRepository repository;
	
	public Fabricante incluir(Fabricante Fabricante) {
		Fabricante.setId(null);
		return repository.save(Fabricante);
	}
	
	public Fabricante alterar(Fabricante Fabricante) {
		pesquisaPorId(Fabricante.getId());
		return repository.save(Fabricante);
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}
	
	public List<Fabricante> listar(){
		return repository.findAll();
	}
	
	public Fabricante pesquisaPorId(Long id) {
		return repository.findById(id).orElseThrow(()
				-> new EmptyResultDataAccessException(0));
	}

}
