package com.alura.aurelio.videosbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.aurelio.videosbackend.domain.Categoria;
import com.alura.aurelio.videosbackend.infra.CustomException;
import com.alura.aurelio.videosbackend.repository.CategoriaRepository;
import com.alura.aurelio.videosbackend.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	@Autowired CategoriaRepository repository;

	@Override
	public List<Categoria> obterTodos() {
		return repository.findAll();
	}

	@Override
	public Optional<Categoria> obter(Long id) throws CustomException {
		validaId(id);
		return repository.findById(id);
	}

	@Override
	public void criar(Categoria categoria) {
		repository.save(categoria);
		
	}

	@Override
	public void atualizar(Categoria categoria, Long id) {
		categoria.setId(id);
		repository.save(categoria);
		validaId(id);
		
	}

	@Override
	public void remover(Long id) {
		validaId(id);
		repository.deleteById(id);
		
	}

	private void validaId(Long id) throws CustomException {
		if(!repository.existsById(id)) {
			throw new CustomException("Este ID não existe.");
		}
	}

}
