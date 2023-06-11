package com.alura.aurelio.videosbackend.controller;

import java.util.ArrayList;
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
import com.alura.aurelio.videosbackend.domain.Video;
import com.alura.aurelio.videosbackend.domain.VideoOutputDTO;
import com.alura.aurelio.videosbackend.infra.CustomException;
import com.alura.aurelio.videosbackend.service.CategoriaService;
import com.alura.aurelio.videosbackend.service.VideoService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
	
	CategoriaService service;
	VideoService videoService;
	
	@Autowired 
	public CategoriaController(CategoriaService service, VideoService videoService) {
		this.service = service;
		this.videoService = videoService;
	}

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
		
	}
	
	@PutMapping(value={"/","/{id}"})
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void put(@Validated(CategoriaInputDTO.class) @RequestBody CategoriaInputDTO categoriaDTO, 
					@PathVariable(name="id",required = false) Long id) throws CustomException {
		
		if(idIsNull(id)) {
			throw new CustomException("ID não pode ser nulo.");
		}
		
		service.atualizar(categoriaDTO, id);
	}
	
	@DeleteMapping(value={"/","/{id}"})
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name="id",required = false) Long id) throws CustomException {
		
		if(idIsNull(id)) {
			throw new CustomException("ID não pode ser nulo.");
		}
		
		service.remover(id);
	}
	
	

	@GetMapping("/{id}/videos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<VideoOutputDTO> getVideosByCategoryId(@PathVariable Long id) throws CustomException {
		List<Video> inVideos = videoService.obterVideosPorIdCategoria(id);
		List<VideoOutputDTO> outVideos = new ArrayList<VideoOutputDTO>();
		
		inVideos.forEach(inVideo -> outVideos.add(videoToOutput(inVideo)));
		
		return outVideos;
	}
	
	private VideoOutputDTO videoToOutput(Video video) {
		return new VideoOutputDTO(
				video.getId(),
				video.getTitulo(),
				video.getDescricao(),
				video.getUrl(),
				video.getCategoria().getId()
				);
	};
	
	public boolean idIsNull(Long id) {
		return id==null;
	}
	
}
