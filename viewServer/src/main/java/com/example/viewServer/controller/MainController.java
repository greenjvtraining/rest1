package com.example.viewServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String root() {
		return "index";
	}
	
	@RequestMapping("/regMember")
	public String regMember() {
		return "joinForm";
	}
	
	@RequestMapping("/login")
	public String loginMember() {
		return "loginForm";
	}
	@RequestMapping("/member/regBoard")
	public String regBoard() {
		return "member/regBoardForm";
	}
	
	@RequestMapping("/member/getBoardList")
	public String getBoardList() {
		
		return "member/boardList";
	}
	
	@RequestMapping("/member/getBoard")
	public String getBoard() {
		return "member/board";
	}
}
