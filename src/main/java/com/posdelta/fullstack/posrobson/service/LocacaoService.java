package com.posdelta.fullstack.posrobson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.posdelta.fullstack.posrobson.model.Locacao;
import com.posdelta.fullstack.posrobson.repository.LocacaoRepository;

@Service
public class LocacaoService {

	@Autowired
	private LocacaoRepository repository;
	
	public Locacao incluir(Locacao locacao) {
		locacao.setId(null);
		return repository.save(locacao);
	}
	
	public Locacao alterar(Locacao locacao) {
		pesquisaPorId(locacao.getId());
		return repository.save(locacao);
	}
	
	public void excluir(Long id) {
		repository.deleteById(id);
	}
	
	public List<Locacao> listar(){
		return repository.findAll();
	}
	
	public Locacao pesquisaPorId(Long id) {
		return repository.findById(id).orElseThrow(()
				-> new EmptyResultDataAccessException(0));
	}
	
}
