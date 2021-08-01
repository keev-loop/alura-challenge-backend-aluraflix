package br.com.aluraflix.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "video")
public class VideoModel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "video_id")
	private long videoId;
	
	@Column(nullable = false, name = "video_titulo")
	private String videoTitulo;
	
	@Column(nullable = false, name = "video_descricao")
	private String videoDescricao;
	
	@Column(nullable = false, name = "video_url")
	private String videoUrl;
	
	
}