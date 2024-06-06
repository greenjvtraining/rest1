package com.example.apiServer.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDto {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private MultipartFile file;
	
	public String getFileName() {
		return file.getOriginalFilename();
	}
}
