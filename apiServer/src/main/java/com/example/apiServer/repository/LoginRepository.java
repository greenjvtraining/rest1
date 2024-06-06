package com.example.apiServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apiServer.entity.Member;

public interface LoginRepository extends JpaRepository<Member, String>{
	
	public Member findByUsernameAndPassword(String username, String passowrd);
}
