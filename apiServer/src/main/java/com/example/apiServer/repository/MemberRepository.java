package com.example.apiServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apiServer.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	
	
}
