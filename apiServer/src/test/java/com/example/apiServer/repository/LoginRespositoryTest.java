package com.example.apiServer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.apiServer.entity.Member;

@SpringBootTest
public class LoginRespositoryTest {
	
	@Autowired
	LoginRepository loginRepository;
	
	@Test
	public void loginTest() {
		String username = "user01";
		String password = "1234";
		
		Member member = loginRepository.findByUsernameAndPassword(username, password);
		System.out.println(member);
		
		assertEquals(member.getUsername(), username);
	}
}
