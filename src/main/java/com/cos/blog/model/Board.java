package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//auto_increment
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob		//대용량 데이터시 사용
	private String content;	//섬머노트 라이브러리<html>태그가 섞여서 디자인이 됨.
	
	@ColumnDefault("0")
	private int count;	// 조회수
	
	@ManyToOne	//Many = Board, One = User 한명의 유저는 여러개의 게시물 작성 가능 (연관관계)
	@JoinColumn(name="userId")	//필드명은 userId User 객체니까 user.java의 user 참조
	private User user;	//DB는 오브젝트를 저장할 수 없다. ForeignKey, 자바는 오브젝트를 저장할 수 있다.
	
	@CreationTimestamp
	private Timestamp createDate;
}
