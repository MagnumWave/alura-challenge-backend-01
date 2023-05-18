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
	public void criar(CategoriaInputDTO categoriaInputDto) {
		validaDTO(categoriaInputDto);
		repository.save(dtoToCategoria(categoriaInputDto, null));
	}

	@Override
	public void atualizar(CategoriaInputDTO categoriaInputDto, Long id) {
		validaId(id);
		validaDTO(categoriaInputDto);
		repository.save(dtoToCategoria(categoriaInputDto, id));
	}

	@Override
	public void remover(Long id) {
		validaId(id);
		repository.deleteById(id);
		
	}
	
	private Categoria dtoToCategoria(CategoriaInputDTO categoriaInputDto, Long id) {
		return new Categoria(
				id,
				categoriaInputDto.getTitulo(),
				categoriaInputDto.getCor()
				);
	}

	private void validaId(Long id) throws CustomException {
		if(!repository.existsById(id)) {
			throw new CustomException("Este ID não existe.");
		}
	}
	
	private void validaDTO(CategoriaInputDTO categoriaInputDto) {
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
		
		if(!erros.isEmpty()) {
			throw new CustomException("Esta requisição apresentou os seguintes erros: ", erros);
		}
	}

}
