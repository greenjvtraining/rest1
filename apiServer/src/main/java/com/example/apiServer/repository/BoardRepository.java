package com.example.apiServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apiServer.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
