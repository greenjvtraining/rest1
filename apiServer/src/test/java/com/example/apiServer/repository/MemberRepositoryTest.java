package com.example.apiServer.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.apiServer.entity.Member;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void createTest() {
		Member member = Member.builder()
				.username("user01")
				.password("1234")
				.name("홍길동")
				.role("ROLE_MEMBER")
				.build();
		
		System.out.println(member);
		Member result = memberRepository.save(member);
		
		assertEquals(member.getName(), result.getName());
	}
}
