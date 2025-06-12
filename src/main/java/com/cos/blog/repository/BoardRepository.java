package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	//JpaRepository < 여기에 함수가 다 들어있음.(findall)
}
