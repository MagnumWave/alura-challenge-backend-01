package com.alura.aurelio.videosbackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "VIDEOS")
@NoArgsConstructor
@AllArgsConstructor
public class Video {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "TITULO", nullable = false, length = 70)
	private String titulo;
	
	@Column(name = "DESCRICAO", nullable = true, length = 250)
	private String descricao;
	
	@Column(name = "URL", nullable = false, length = 70)
	private String url;
	
	
}
