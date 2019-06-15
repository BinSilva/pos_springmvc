package com.posdelta.fullstack.posrobson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.posdelta.fullstack.posrobson.model.Locacao;
import com.posdelta.fullstack.posrobson.model.Motorista;

public interface MotoristaRepository extends JpaRepository<Motorista, Long>{

}
