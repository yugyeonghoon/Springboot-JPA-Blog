package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// @DynamicInsert	//insert 시에 null값인 어노테이션 빼고  insert
//ORM -> Java(다른언어) Object ->테이블로 매핑해주는 기술 object를 만들면 JPA 가 테이블로 바꿔줌.
@Entity	//user클래스가 자동으로 mysql에 태이블이 생성된다.
public class User {
	 
	@Id	//primarykey
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 프로젝트에서 연결된 데이터베이스의 넘버링 전략을 따라간다. = 오토인크리먼트
	private int id;	//orcle = 시퀀스, mysql = auto_increment
	
	@Column(nullable = false, length = 20, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false, length = 50)	//123456 -> 해쉬 (비밀번호 암호화) 때문에 length를 좀 길게 설정
	private String password; // 비밀번호
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("user") <-디폴트값을 user로 고정 하는 것보단 밑에 Enum 을 사용하여 하는게 실수가 없음
	//DB는 RoleType이라는게 없다.
	@Enumerated(EnumType.STRING)
	private RoleType role;	//Enum을 쓰는게 좋다. -  Enum을 쓰면 도메인(범위 ex성별 남, 여)을 만들어줌 // ADMIN, USER (권한부여)
	
	@CreationTimestamp //시간이 자동입력
	private Timestamp createDate;	//생성일
}
