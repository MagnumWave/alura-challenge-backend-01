package com.alura.aurelio.videosbackend.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alura.aurelio.videosbackend.domain.Video;
import com.alura.aurelio.videosbackend.infra.CustomException;
import com.alura.aurelio.videosbackend.repository.VideoRepository;
import com.alura.aurelio.videosbackend.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	
	@Autowired VideoRepository repository;

	@Override
	public List<Video> obterTodos() {
		return repository.findAll();
	}

	@Override
	public Optional<Video> obter(Long id) {
		validaId(id);
		return repository.findById(id);
	}

	@Override
	public void criar(Video video) {
		repository.save(video);
		
	}

	@Override
	public void atualizar(Video video, Long id) {
		video.setId(id);
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
