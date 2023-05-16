package com.alura.aurelio.videosbackend.service;

import java.util.List;
import java.util.Optional;

import com.alura.aurelio.videosbackend.domain.Categoria;
import com.alura.aurelio.videosbackend.infra.CustomException;

public interface CategoriaService {
	
	List<Categoria> obterTodos();
	Optional<Categoria> obter(Long id) throws CustomException;
	void criar(Categoria categoria);
	void atualizar(Categoria categoria, Long id) throws CustomException;
	void remover(Long id) throws CustomException;
	
}
