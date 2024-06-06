package com.example.apiServer.entity;

import lombok.Data;

@Data
public class Board2 {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private String originName;
	private String newName;
	private String thumnailName;
}
