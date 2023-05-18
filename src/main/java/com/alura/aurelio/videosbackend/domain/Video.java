package com.alura.aurelio.videosbackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "VIDEOS")
@SecondaryTable(name = "CATEGORIAS")
@NoArgsConstructor
@AllArgsConstructor
public class Video {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "TITULO", nullable = false, length = 70)
	private String titulo;
	
	@Column(name = "DESCRICAO", nullable = false, length = 250)
	private String descricao;
	
	@Column(name = "URL", nullable = false, length = 70)
	private String url;
	
	
	//@OneToMany
	@OneToOne
	//@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="ID_CATEGORIA")
	private Categoria categoria;
	
	
}
