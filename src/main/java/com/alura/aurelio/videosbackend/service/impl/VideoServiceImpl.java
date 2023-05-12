package com.alura.aurelio.videosbackend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.aurelio.videosbackend.domain.Video;
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
		return repository.findById(id);
	}

	@Override
	public void criar(Video video) {
		System.out.println("capturado:");
		System.out.println(video);
		//repository.save(video);
		//TODO: avaliar o que rolava aqui..
		
	}

	@Override
	public void atualizar(Video video, Long id) {
		video.setId(id);
		repository.save(video);
		//TODO: impedir atualização de ID inexistente.
		
	}

	@Override
	public void remover(Long id) {
		repository.deleteById(id);
		//TODO: impedir deleção de ID inexistente.
		
	}

}
