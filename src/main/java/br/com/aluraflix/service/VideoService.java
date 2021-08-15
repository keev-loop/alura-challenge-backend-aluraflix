package br.com.aluraflix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.aluraflix.model.VideoModel;
import br.com.aluraflix.repository.VideoRepository;

@Service
public class VideoService {
	
	
	@Autowired
	private VideoRepository _videorepo;
	
	
	public ResponseEntity<VideoModel> updating(long videoId, VideoModel novoVideo) {
		Optional<VideoModel> antigoVideo = _videorepo.findById(videoId);
		if(antigoVideo.isPresent()) {
			VideoModel video = antigoVideo.get();
			video.setVideoTitulo(novoVideo.getVideoTitulo());
			video.setVideoDescricao(novoVideo.getVideoDescricao());
			video.setVideoUrl(novoVideo.getVideoUrl());
			_videorepo.save(video);
			return new ResponseEntity<VideoModel>(video, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	
	public ResponseEntity<VideoModel> deleting(long videoId) {
		Optional<VideoModel> video = _videorepo.findById(videoId);
		if(video.isPresent()) {
			_videorepo.delete(video.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	public List<VideoModel> pagination(int page) {
        int size = 5;
        PageRequest pageRequest = PageRequest.of(page,size);
        return _videorepo.findAll(pageRequest).getContent();
    }
	
	
}
