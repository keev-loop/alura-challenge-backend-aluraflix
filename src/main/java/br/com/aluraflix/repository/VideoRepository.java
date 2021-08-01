package br.com.aluraflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aluraflix.model.VideoModel;

@Repository
public interface VideoRepository extends JpaRepository<VideoModel, Long> {

}
