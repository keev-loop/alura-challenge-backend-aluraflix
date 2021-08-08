package br.com.aluraflix.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aluraflix.model.CategoriaModel;
import br.com.aluraflix.model.VideoModel;

@Repository
public interface VideoRepository extends JpaRepository<VideoModel, Long> {

	
	Optional<VideoModel> findByVideoTitulo(String videoTitulo);
	
	
	List<VideoModel> findByCategoriaId(CategoriaModel categoriaId);
	
	
}
