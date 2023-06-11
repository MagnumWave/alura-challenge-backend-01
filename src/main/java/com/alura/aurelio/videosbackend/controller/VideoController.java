package com.alura.aurelio.videosbackend.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alura.aurelio.videosbackend.domain.Video;
import com.alura.aurelio.videosbackend.domain.VideoInputDTO;
import com.alura.aurelio.videosbackend.domain.VideoOutputDTO;
import com.alura.aurelio.videosbackend.infra.CustomException;
import com.alura.aurelio.videosbackend.service.VideoService;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/videos")
public class VideoController {
	
	private VideoService service;
	
	@Autowired 
	public VideoController(VideoService service){
		this.service = service;
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<VideoOutputDTO> getAll(@RequestParam(name = "search", required = false)
										String search) {

		List<Video> inVideos = new ArrayList<Video>();
		
		if(StringUtils.isNotBlank(search)) {
			inVideos = service.obterVideosPorTituloContendo(search);
		} else {
			inVideos = service.obterTodos();
		}
		
		List<VideoOutputDTO> outVideos = new ArrayList<VideoOutputDTO>();
		inVideos.forEach(inVideo -> outVideos.add(videoToOutput(inVideo)));
		return outVideos;
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public VideoOutputDTO getById(@PathVariable Long id) throws CustomException {
		Video inVideo = service.obter(id).get();
		return videoToOutput(inVideo);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void post(@Validated(VideoInputDTO.class) @RequestBody VideoInputDTO videoInputDTO) throws CustomException {
		service.criar(videoInputDTO);
	}
	
	@PutMapping(value={"/","/{id}"})
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void put(@Validated(VideoInputDTO.class) @RequestBody VideoInputDTO videoDTO, 
					@PathVariable(name="id",required = false) Long id) throws CustomException {
		
		if(idIsNull(id)) {
			throw new CustomException("ID não pode ser nulo.");
		}
		
		service.atualizar(videoDTO, id);
	}
	
	@DeleteMapping(value={"/","/{id}"})
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name="id",required = false) Long id) throws CustomException {
		
		if(idIsNull(id)) {
			throw new CustomException("ID não pode ser nulo.");
		}
		
		service.remover(id);
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
