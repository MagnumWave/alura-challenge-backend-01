package com.alura.aurelio.videosbackend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.aurelio.videosbackend.domain.Categoria;
import com.alura.aurelio.videosbackend.domain.CategoriaInputDTO;
import com.alura.aurelio.videosbackend.infra.CustomException;
import com.alura.aurelio.videosbackend.repository.CategoriaRepository;
import com.alura.aurelio.videosbackend.service.CategoriaService;

import io.micrometer.common.util.StringUtils;

@Service
public class CategoriaServiceImpl implements CategoriaService {
	
	CategoriaRepository repository;
	
	@Autowired 
	public CategoriaServiceImpl(CategoriaRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Categoria> obterTodos() {
		return repository.findAll();
	}

	@Override
	public Optional<Categoria> obter(Long id) throws CustomException {
		if(!idIsValid(id)) {
			throw new CustomException("Este ID não existe.");
		}
		return repository.findById(id);
	}

	@Override
	public void criar(CategoriaInputDTO categoriaInputDto) throws CustomException {
		List<String> erros = errosDoDTO(categoriaInputDto);
		if(!erros.isEmpty()) {
			throw new CustomException("Esta requisição apresentou os seguintes erros: ", erros);
		}
		
		repository.save(dtoToCategoria(categoriaInputDto, null));
	}

	@Override
	public void atualizar(CategoriaInputDTO categoriaInputDto, Long id) throws CustomException {
		if(!idIsValid(id)) {
			throw new CustomException("Este ID não existe.");
		}
		
		List<String> erros = errosDoDTO(categoriaInputDto);
		if(!erros.isEmpty()) {
			throw new CustomException("Esta requisição apresentou os seguintes erros: ", erros);
		}
		
		repository.save(dtoToCategoria(categoriaInputDto, id));
	}

	@Override
	public void remover(Long id) throws CustomException {
		boolean idIsValid = idIsValid(id);
		if(!idIsValid) {
			throw new CustomException("Este ID não existe.");
		}
		repository.deleteById(id);
		
	}
	
	private Categoria dtoToCategoria(CategoriaInputDTO categoriaInputDto, Long id) {
		return new Categoria(
				id,
				categoriaInputDto.getTitulo(),
				categoriaInputDto.getCor()
				);
	}

	public boolean idIsValid(Long id) throws IllegalArgumentException {
		return repository.existsById(id);
	}
	
	public List<String> errosDoDTO(CategoriaInputDTO categoriaInputDto) {
		List<String> erros = new ArrayList<>();
	
		if(StringUtils.isEmpty(categoriaInputDto.getTitulo()) || StringUtils.isBlank(categoriaInputDto.getTitulo())) {
			erros.add("Título da categoria não pode ser nulo ou vazio.");
		} else if(categoriaInputDto.getTitulo().length() > 30) {
			erros.add("Título da categoria não pode ter mais do que 30 caracteres.");
		}
		
		if(StringUtils.isEmpty(categoriaInputDto.getCor()) || StringUtils.isBlank(categoriaInputDto.getCor())) {
			erros.add("Cor da categoria não pode ser nula ou vazia.");
		} else if(categoriaInputDto.getCor().length() > 30) {
			erros.add("Cor da categoria não pode ter mais do que 30 caracteres.");
		}
		
		return erros;
	}
}
