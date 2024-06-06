package com.example.apiServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiServer.entity.Member;
import com.example.apiServer.repository.LoginRepository;
import com.example.apiServer.repository.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RestController
public class MainController {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private LoginRepository loginRepository;
	
	@RequestMapping("/")
	public String root() {
		return "index";
	}
	
	@GetMapping("/join")
	public String join() {
		return "joinForm";
	}
	
	@PostMapping("/joinProc")//완료
	public Member joinProc(Member member) {
		
		Member m = memberRepository.save(member);
		
		return m;
	}
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@PostMapping("/loginProc")
	public Member loginProc(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
		Member member = loginRepository.findByUsernameAndPassword(username, password);
		
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		session.setAttribute("name", member.getName());
		
		return member;
	}
}
