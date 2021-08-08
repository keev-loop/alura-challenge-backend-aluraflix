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
@Table(name = "categoria")
public class CategoriaModel {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "categoria_id")
	private long categoriaId;
	
	@Column(nullable = false, name = "categoria_titulo")
	private String categoriaTitulo;
	
	@Column(nullable = false, name = "categoria_cor")
	private String categoriaCor;
	

}
