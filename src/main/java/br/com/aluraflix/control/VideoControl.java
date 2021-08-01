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

import br.com.aluraflix.model.VideoModel;
import br.com.aluraflix.repository.VideoRepository;
import br.com.aluraflix.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Videos")
public class VideoControl {
	

	@Autowired
	private VideoRepository _videorepo;
	@Autowired
	private VideoService _videoserv;
	
	@ApiOperation(value = "Visualizar todos os videos!")
	@GetMapping(value = "/videos")
	public List<VideoModel> readAll() {
		return _videorepo.findAll();
	}
	
	
	@ApiOperation(value = "Vizualizar um video pelo ID!")
	@GetMapping(value = "/videos/{videoId}")
	public ResponseEntity<VideoModel> readOneById(@PathVariable(value = "videoId") long videoId) {
		Optional<VideoModel> video = _videorepo.findById(videoId);
		if(video.isPresent())
			return new ResponseEntity<VideoModel>(video.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	

	@ApiOperation(value = "Criar um video pelo JSON!")
	@PostMapping(value = "/videos")
	public ResponseEntity<?> createOneWithBody(@Validated @RequestBody VideoModel video){
		try {
			return new ResponseEntity<VideoModel>(_videorepo.save(video), HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(e, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	} 
	

	@ApiOperation(value = "Alterar um video pelo ID, com o JSON!")
	@PutMapping(value = "/videos/{videoId}")
	public ResponseEntity<VideoModel> updateOneById(@PathVariable(value = "videoId") long videoId, @Validated @RequestBody VideoModel novoVideo) {
		return _videoserv.updating(videoId, novoVideo);
	}
	
	
	@ApiOperation(value = "Apagar um video pelo ID!")
	@DeleteMapping(value = "/videos/{videoId}")
	public ResponseEntity<VideoModel> deleteOneById(@PathVariable(value = "videoId") long videoId) {
		return _videoserv.deleting(videoId);
	}
	

}
