package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.BoardImageDAO;
import com.example.demo.vo.BoardImage;

import lombok.Setter;

@Service
@Setter
public class BoardImageService {
	@Autowired(required = true)
	private BoardImageDAO dao;

	public int getNextBoardImageNum() {
		return dao.getNextBoardImageNum();
	}

	public List<BoardImage> findAll() {
		return dao.findAll();
	}

	private final Path rootLocation;

	public BoardImageService(String uploadPath) {
	    this.rootLocation = Paths.get(uploadPath);
	    System.out.println(rootLocation.toString());
	}

	public BoardImage store(MultipartFile file) throws Exception {
		try {
			if (file.isEmpty()) {
				throw new Exception("Failed to store empty file " + file.getOriginalFilename());
				}
			String saveFileName = fileSave(rootLocation.toString(), file);
			BoardImage saveFile = new BoardImage();
			saveFile.setBoard_image_num(getNextBoardImageNum());
			saveFile.setBoard_image_file(file.getOriginalFilename());
			saveFile.setSaved_name(saveFileName);
			saveFile.setContentType(file.getContentType());
			saveFile.setFile_path(rootLocation.toString().replace(File.separatorChar, '/') + '/' + saveFileName);
			dao.save(saveFile);
			return saveFile;

		} catch (IOException e) {
			throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
		}
	}

	public BoardImage load(int board_image_num) {
		return dao.findById(board_image_num).get();
	}

	public String fileSave(String rootLocation, MultipartFile file) throws IOException {
		File uploadDir = new File(rootLocation);

		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		// saveFileName 생성
		UUID uuid = UUID.randomUUID();
		String saveFileName = uuid.toString() + file.getOriginalFilename();
		File saveFile = new File(rootLocation, saveFileName);
		FileCopyUtils.copy(file.getBytes(), saveFile);
		return saveFileName;
	}
}