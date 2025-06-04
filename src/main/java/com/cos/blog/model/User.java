package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity	//user클래스가 자동으로 mysql에 태이블이 생성된다.
public class User {
	
	@Id	//primarykey
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 프로젝트에서 연결된 데이터베이스의 넘버링 전략을 따라간다. = 오토인크리먼트
	private int id;	//orcle = 시퀀스, mysql = auto_increment
	
	@Column(nullable = false, length = 20)
	private String username; // 아이디
	
	@Column(nullable = false, length = 50)	//123456 -> 해쉬 (비밀번호 암호화) 때문에 length를 좀 길게 설정
	private String password; // 비밀번호
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@CreationTimestamp //시간이 자동입력
	private Timestamp createDate;	//생성일
}
