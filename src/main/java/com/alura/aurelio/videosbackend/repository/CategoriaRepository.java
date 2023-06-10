package com.alura.aurelio.videosbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alura.aurelio.videosbackend.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	
	@Query(value="SELECT CATEGORIAS.ID from CATEGORIAS order by ID desc limit 1",nativeQuery = true)
	Long getLastId();
}
