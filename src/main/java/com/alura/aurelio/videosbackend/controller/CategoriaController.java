package com.alura.aurelio.videosbackend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.alura.aurelio.videosbackend.domain.Categoria;
import com.alura.aurelio.videosbackend.domain.CategoriaInputDTO;
import com.alura.aurelio.videosbackend.infra.CustomException;
import com.alura.aurelio.videosbackend.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	@Autowired CategoriaService service;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Categoria> getAll() {
		return service.obterTodos();
		
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<Categoria> getById(@PathVariable Long id) throws CustomException {
		return service.obter(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void post(@Validated(CategoriaInputDTO.class) @RequestBody CategoriaInputDTO categoriaDTO) throws CustomException {
		service.criar(categoriaDTO);
		//service.criar(categoriaDTO.toCategoria());
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void put(@Validated(CategoriaInputDTO.class) @RequestBody CategoriaInputDTO categoriaDTO, @PathVariable Long id) throws CustomException {
		service.atualizar(categoriaDTO, id);
		//service.atualizar(categoriaDTO.toCategoria(), id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws CustomException {
		service.remover(id);
	}
	
//	@GetMapping("/{id}/videos")
//	@ResponseStatus(code = HttpStatus.OK)
//	public Optional<Categoria> getById(@PathVariable Long id) throws CustomException {
//		return service.obter(id);
//	}
	
}
