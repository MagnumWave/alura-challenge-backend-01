package com.alura.aurelio.videosbackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "VIDEOS")
public class Video {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "TITULO", nullable = false)
	private String titulo;
	
	@Column(name = "DESCRICAO", nullable = true)
	private String descricao;
	
	@Column(name = "URL", nullable = false)
	private String url;
	
}
