package com.example.apiServer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bno;
	private String title;
	private String content;
	@ManyToOne
	@JoinColumn(name="writer")
	private Member member;
	private String originName;
	private String newName;
	private String thumnailName;
}
