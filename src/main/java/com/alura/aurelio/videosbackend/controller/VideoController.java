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

import com.alura.aurelio.videosbackend.domain.Video;
import com.alura.aurelio.videosbackend.domain.VideoInputDTO;
import com.alura.aurelio.videosbackend.infra.CustomException;
import com.alura.aurelio.videosbackend.service.VideoService;

@RestController
@RequestMapping("/videos")
public class VideoController {
	
	@Autowired VideoService service;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Video> getAll() {
		return service.obterTodos();
		
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Optional<Video> getById(@PathVariable Long id) throws CustomException {
		return service.obter(id);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void post(@Validated(VideoInputDTO.class) @RequestBody VideoInputDTO videoDTO) throws CustomException {
		service.criar(videoDTO.toVideo());
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void put(@Validated(VideoInputDTO.class) @RequestBody VideoInputDTO videoDTO, @PathVariable Long id) throws CustomException {
		service.atualizar(videoDTO.toVideo(), id);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) throws CustomException {
		service.remover(id);
	}
	
	//TODO: no banco, criar uma tabela CATEGORIAS com (id, titulo, cor)
	//TODO: id da categoria é PK, e id=1 significa a categoria LIVRE
	//TODO: id = 1 é valor default pros videos que foram criados sem categoria
	//TODO: id, titulo e cor são todos campos obrigatórios
	
	
	//TODO: a tabela vídeos vai ganhar uma FK chamada "categoriaID",
	//obviamente para fazer a relação das tabelas.
	
	//TODO: " GET categorias/:id/videos/ " deve listar todos os videos DA MESMA categoria.
	//(escolhendo a categoria X no ID, mostre todos os videos dela.)
	
	//TODO: " GET /videos/?search=jogos " (query params. search pelo título/nome.)
	//filtra videos que contenham o valor definiddo na query params.
	
}
