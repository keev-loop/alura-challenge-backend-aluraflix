package br.com.aluraflix.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.aluraflix.model.CategoriaModel;
import br.com.aluraflix.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	
	@Autowired
	private CategoriaRepository _categoriarepo;
	
	
	public ResponseEntity<CategoriaModel> updating(long categoriaId, CategoriaModel novaCategoria) {
		Optional<CategoriaModel> antigaCategoria = _categoriarepo.findById(categoriaId);
		if(antigaCategoria.isPresent()) {
			CategoriaModel categoria = antigaCategoria.get();
			categoria.setCategoriaTitulo(novaCategoria.getCategoriaTitulo());
			categoria.setCategoriaCor(novaCategoria.getCategoriaCor());
			_categoriarepo.save(categoria);
			return new ResponseEntity<CategoriaModel>(HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	public ResponseEntity<CategoriaModel> deleting(long categoriaId) {
		Optional<CategoriaModel> categoria = _categoriarepo.findById(categoriaId);
		if(categoria.isPresent()) {
			_categoriarepo.delete(categoria.get());
			return new ResponseEntity<CategoriaModel>(HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
