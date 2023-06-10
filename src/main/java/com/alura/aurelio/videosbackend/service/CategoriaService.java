package com.alura.aurelio.videosbackend.service;

import java.util.List;
import java.util.Optional;

import com.alura.aurelio.videosbackend.domain.Categoria;
import com.alura.aurelio.videosbackend.domain.CategoriaInputDTO;
import com.alura.aurelio.videosbackend.infra.CustomException;

public interface CategoriaService {
	
	List<Categoria> obterTodos();
	Optional<Categoria> obter(Long id) throws CustomException;
	void criar(CategoriaInputDTO categoria);
	void atualizar(CategoriaInputDTO categoria, Long id) throws CustomException;
	void remover(Long id) throws CustomException;
	
	Long obterUltimoID();
	
}
