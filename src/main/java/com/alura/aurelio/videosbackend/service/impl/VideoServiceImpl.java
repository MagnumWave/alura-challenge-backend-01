package com.alura.aurelio.videosbackend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.aurelio.videosbackend.domain.Categoria;
import com.alura.aurelio.videosbackend.domain.Video;
import com.alura.aurelio.videosbackend.domain.VideoInputDTO;
import com.alura.aurelio.videosbackend.infra.CustomException;
import com.alura.aurelio.videosbackend.repository.CategoriaRepository;
import com.alura.aurelio.videosbackend.repository.VideoRepository;
import com.alura.aurelio.videosbackend.service.VideoService;

import io.micrometer.common.util.StringUtils;

@Service
public class VideoServiceImpl implements VideoService {
	
	VideoRepository repository;
	CategoriaRepository categoriaRepository;
	
	@Autowired 
	public VideoServiceImpl(VideoRepository repository, CategoriaRepository categoriaRepository) {
		this.repository = repository;
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public List<Video> obterTodos() {
		return repository.findAll();
	}
	
	@Override
	public List<Video> obterVideosPorTituloContendo(String search) {
		return repository.findByTituloContaining(search);
	}

	@Override
	public Optional<Video> obter(Long id) throws CustomException {
		
		if(!isVideoIdValid(id)) {
			throw new CustomException("Este ID de vídeo não existe.");
		}
		
		return repository.findById(id);
	}

	@Override
	public void criar(VideoInputDTO videoInputDto) {
		System.out.println(videoInputDto);
		videoInputDto = desnulificadorDeIdCategoria(videoInputDto);
		System.out.println(videoInputDto);
		
		if(!isCategoriaIdValid(videoInputDto.getIdCategoria())) {
			throw new CustomException("Este ID de categoria não existe.");
		}
		
		List<String> errosDoDTO = errosDoDTO(videoInputDto);
		if(!errosDoDTO.isEmpty()) {
			throw new CustomException("Esta requisição apresentou os seguintes erros: ", errosDoDTO);
		}
		
		repository.save(dtoToVideo(videoInputDto, null));
	}

	@Override
	public void atualizar(VideoInputDTO videoInputDto, Long id) {
		videoInputDto = desnulificadorDeIdCategoria(videoInputDto);
		
		if(!isVideoIdValid(id)) {
			throw new CustomException("Este ID de vídeo não existe.");
		}
		
		if(!isCategoriaIdValid(id)) {
			throw new CustomException("Este ID de categoria não existe.");
		}
		
		
		List<String> errosDoDTO = errosDoDTO(videoInputDto);
		if(!errosDoDTO.isEmpty()) {
			throw new CustomException("Esta requisição apresentou os seguintes erros: ", errosDoDTO);
		}
		
		repository.save(dtoToVideo(videoInputDto, id));
	}

	@Override
	public void remover(Long id) {
		
		if(!isVideoIdValid(id)) {
			throw new CustomException("Este ID de vídeo não existe.");
		}
		repository.deleteById(id);
	}
	
	@Override
	public List<Video> obterVideosPorIdCategoria(Long id) throws CustomException {
		
		if(!isCategoriaIdValid(id)) {
			throw new CustomException("Este ID de categoria não existe.");
		}
		Categoria categoria = categoriaRepository.findById(id).get();
		return repository.findByCategoria(categoria);
	}

	private VideoInputDTO desnulificadorDeIdCategoria(VideoInputDTO videoInputDto) {
		if(videoInputDto.getIdCategoria() == null) {
			videoInputDto.setIdCategoria(1L);
		}
		
		return videoInputDto;
	}
	
	private Video dtoToVideo(VideoInputDTO videoInputDto, Long id) {
		return new Video(
				id,
				videoInputDto.getTitulo(),
				videoInputDto.getDescricao(),
				videoInputDto.getUrl(),
				categoriaRepository.findById(videoInputDto.getIdCategoria()).get()
				);
	}

	public boolean isVideoIdValid(Long id){
		return repository.existsById(id);
	}
	
	public boolean isCategoriaIdValid(Long id) {
		return categoriaRepository.existsById(id);
	}
	
	public List<String> errosDoDTO(VideoInputDTO videoInputDto) {
		
		List<String> erros = new ArrayList<>();
	
		if(StringUtils.isEmpty(videoInputDto.getTitulo()) || StringUtils.isBlank(videoInputDto.getTitulo())) {
			erros.add("Título do vídeo não pode ser nulo ou vazio.");
		} else if (videoInputDto.getTitulo().length() > 70) {
			erros.add("Título do vídeo não pode ter mais do que 70 caracteres.");
		}
		
		if(StringUtils.isEmpty(videoInputDto.getDescricao()) || StringUtils.isBlank(videoInputDto.getDescricao())) {
			erros.add("Descrição do vídeo não pode ser nula ou vazia.");
		} else if(videoInputDto.getDescricao().length() > 250) {
			erros.add("Descrição do vídeo não pode ter mais do que 250 caracteres.");
		}
		
		if(StringUtils.isEmpty(videoInputDto.getUrl()) || StringUtils.isBlank(videoInputDto.getUrl())) {
			erros.add("Url do vídeo não pode ser nula ou vazia.");
		} else if(videoInputDto.getUrl().length() > 70) {
			erros.add("Url do vídeo não pode ter mais do que 70 caracteres.");
		} else if (!videoInputDto.getUrl().matches("[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)")) {
			erros.add("Url do vídeo não tem formato de url.");
		}
		
		
		return erros;
	}

	
	

	

}
