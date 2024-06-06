package com.example.apiServer.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.apiServer.entity.Board;
import com.example.apiServer.entity.Member;

@SpringBootTest
public class EntityToDtoTest {

	private ModelMapper modelMapper = new ModelMapper();
	
	public BoardDto of(Board board) {
		return modelMapper.map(board, BoardDto.class);
	}
	
	@Test
	public void testConvert() {
		Member member = new Member();
		member.setUsername("user01");
		
		Board board = new Board();
		board.setBno(1);
		board.setTitle("안녕하세요");
		board.setContent("반갑습니다.");
		board.setMember(member);
		
		BoardDto boardDto = of(board);
		System.out.println(board);
		assertNotNull(boardDto);
	}
}
