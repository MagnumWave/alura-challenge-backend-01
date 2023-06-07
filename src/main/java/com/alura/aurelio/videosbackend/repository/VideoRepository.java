package com.alura.aurelio.videosbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.aurelio.videosbackend.domain.Categoria;
import com.alura.aurelio.videosbackend.domain.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{

	List<Video> findByCategoria(Categoria categoria);
	List<Video> findByTituloContaining(String search);
	
}
