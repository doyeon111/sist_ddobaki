package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.BoardImageService;
import com.example.demo.vo.BoardImage;

import lombok.Setter;


@Controller
@Setter
public class BoardImageController {
	@Autowired
	private BoardImageService bis;
	
	@GetMapping("/listBoardImage")
	public List<BoardImage> listBoardImage() {
		return bis.findAll();
	}
	
	@Autowired
	ResourceLoader resourceLoader;

	@PostMapping("/image")
	@ModelAttribute
	public ResponseEntity<?> imageUpload(@RequestParam("file") MultipartFile file) {
		try {
			BoardImage uploadFile = bis.store(file);
			return ResponseEntity.ok().body("/image/" + uploadFile.getBoard_image_num());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/image/{board_image_num}")
	public ResponseEntity<?> serveFile(@PathVariable int board_image_num) {
		try {
			BoardImage uploadFile = bis.load(board_image_num);
			Resource resource = resourceLoader.getResource("file:" + uploadFile.getFile_path());
			return ResponseEntity.ok().body(resource);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}

	}
}	