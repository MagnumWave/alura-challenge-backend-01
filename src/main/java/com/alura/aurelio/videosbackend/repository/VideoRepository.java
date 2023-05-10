package com.alura.aurelio.videosbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.aurelio.videosbackend.domain.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{

}
