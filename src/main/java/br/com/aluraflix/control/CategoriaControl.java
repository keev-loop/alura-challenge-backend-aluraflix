package br.com.aluraflix.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aluraflix.model.CategoriaModel;
import br.com.aluraflix.repository.CategoriaRepository;
import br.com.aluraflix.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Categorias")
public class CategoriaControl {
	
	
	@Autowired
	private CategoriaRepository _categoriarepo;
	@Autowired
	private CategoriaService _categoriaserv;
	
	
	@ApiOperation(value = "Visualizar todas as categorias!")
	@GetMapping(value = "/categorias")
	public List<CategoriaModel> readAll() {
		return _categoriarepo.findAll();
	}

	
	@ApiOperation(value = "Vizualizar uma categoria pelo ID!")
	@GetMapping(value = "/categorias/{categoriaId}")
	public ResponseEntity<CategoriaModel> readOneById(@PathVariable(value="categoriaId") long categoriaId) {
		Optional<CategoriaModel> categoria = _categoriarepo.findById(categoriaId);		
		if(categoria.isPresent()) {
			return new ResponseEntity<CategoriaModel>(categoria.get(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@ApiOperation(value = "Criar uma categoria com o JSON!")
	@PostMapping(value = "/categorias")
	public ResponseEntity<?> createOneWithBody(@Validated @RequestBody CategoriaModel categoria) {
		try {
			return new ResponseEntity<CategoriaModel>(_categoriarepo.save(categoria), HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(e, HttpStatus.NOT_ACCEPTABLE);
		}	
	}
	
	
	@ApiOperation(value = "Atualizar uma categoria pelo ID, com o JSON!")
	@PutMapping(value = "/categorias/{categoriaId}")
	public ResponseEntity<CategoriaModel> updateOneById(@PathVariable(value="categoriaId") long categoriaId, @Validated @RequestBody CategoriaModel novaCategoria) {
		return _categoriaserv.updating(categoriaId, novaCategoria);
	}
	
	
	@ApiOperation(value = "Apagar uma categoria pelo ID!")
	@DeleteMapping(value = "/categorias/{categoriaId}")
	public ResponseEntity<CategoriaModel> deleteOneById(@PathVariable(value="categoriaId") long categoriaId) {
		return _categoriaserv.deleting(categoriaId);
	}
	
		
}