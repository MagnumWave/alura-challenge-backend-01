package com.alura.aurelio.videosbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.aurelio.videosbackend.domain.Video;
import com.alura.aurelio.videosbackend.service.VideoService;

@RestController
@RequestMapping("/videos")
public class VideoController {
	
	@Autowired VideoService service;

	@GetMapping
	public List<Video> getAll() {
		return service.obterTodos();
		
	}
}
