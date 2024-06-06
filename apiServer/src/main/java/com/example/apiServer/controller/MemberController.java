package com.example.apiServer.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiServer.dto.BoardDto;
import com.example.apiServer.entity.Board;
import com.example.apiServer.entity.Board2;
import com.example.apiServer.entity.Member;
import com.example.apiServer.repository.BoardRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import net.coobird.thumbnailator.Thumbnails;

@CrossOrigin("*")
@RestController
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private BoardRepository boardRepository;
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	@RequestMapping("/main")
	public String main(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		model.addAttribute("username", username);
		
		return "member/main";
	}
	
	@RequestMapping("/regBoard")//삭제
	public String regBoard() {
		return "member/regBoardForm";
	}
	
	@RequestMapping("/regBoardProc")//완료
	public String regBoardProc(BoardDto boardDto, HttpServletRequest request, Model model) {
		
		String originName = boardDto.getFileName();
		String newName = UUID.randomUUID().toString() + "_" + originName;
		
		Member member = new Member();
		//Dto -> Entity
		Board board = new Board();
		board.setTitle(boardDto.getTitle());
		board.setContent(boardDto.getContent());
		member.setUsername(boardDto.getWriter());
		board.setMember(member);
		//file로부터 얻어낼 것들...
		board.setOriginName(originName);
		board.setNewName(newName);
		
		//파일저장
		File file = new File(newName);
		
		try {
			boardDto.getFile().transferTo(file);
			System.out.println("파일업로드 성공........");
			
			//썸네일 생성
			String thumnailSaveName = "s_" + newName;
			board.setThumnailName(thumnailSaveName);
			
			File thumanailFile = new File(uploadPath + thumnailSaveName);
			File ufile = new File(uploadPath + newName);
			
			Thumbnails.of(ufile).size(100, 100).toFile(thumanailFile);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		boardRepository.save(board);
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		model.addAttribute("username", username);
		
		return "member/ok";
	}
	
	@RequestMapping("/regBoardProc1")
	public @ResponseBody String regBoardProc1(BoardDto boardDto, HttpServletRequest request, Model model) {
		
		String originName = boardDto.getFileName();
		String newName = UUID.randomUUID().toString() + "_" + originName;
		
		Member member = new Member();
		//Dto -> Entity
		Board board = new Board();
		board.setTitle(boardDto.getTitle());
		board.setContent(boardDto.getContent());
		member.setUsername(boardDto.getWriter());
		board.setMember(member);
		//file로부터 얻어낼 것들...
		board.setOriginName(originName);
		board.setNewName(newName);
		
		//파일저장
		File file = new File(newName);
		
		try {
			boardDto.getFile().transferTo(file);
			System.out.println("파일업로드 성공........");
			
			//썸네일 생성
			String thumnailSaveName = "s_" + newName;
			board.setThumnailName(thumnailSaveName);
			
			File thumanailFile = new File(uploadPath + thumnailSaveName);
			File ufile = new File(uploadPath + newName);
			
			Thumbnails.of(ufile).size(100, 100).toFile(thumanailFile);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		boardRepository.save(board);
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		//model.addAttribute("username", username);
		
		return board.toString();
	}
	
	@RequestMapping("/getBoards")
	public List<Board> getBoards() {
		System.out.println("getBoards...................");
		List<Board> boardList = boardRepository.findAll();
		
		//model.addAttribute("boardList", boardList);
		
		return boardList;
	}
	
	@RequestMapping("/detail")
	public String getDetail(@RequestParam("bno") int bno, Model model) {
		System.out.println("detail............" + bno);
		
		Optional<Board> result = boardRepository.findById(bno);
		Board board = result.get();
		
		model.addAttribute("board", board);
			
		return "member/boardDetail";
	}
	
	@RequestMapping("/getBoard")
	public Board2 getBoard(@RequestParam("bno") int bno) {
		
		System.out.println("getBoard............" + bno);
		
		Optional<Board> result = boardRepository.findById(bno);
		Board board = result.get();
		System.out.println(board);
		
		Board2 b = new Board2();
		b.setBno(board.getBno());
		b.setTitle(board.getTitle());
		b.setContent(board.getContent());
		b.setOriginName(board.getOriginName());
		b.setNewName(board.getNewName());
		b.setWriter(board.getMember().getUsername());
		b.setThumnailName(board.getThumnailName());
		
		return b;
	}
}
