package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

//Java 에선 DAO
// 자동으로 bean 등록이 된다.
// @Repository //생략이가능하다
public interface UserRepository extends JpaRepository<User, Integer>{
	//select * from user where username = 1;
	Optional<User> findByUsername(String username);
}


//JPA Naming 전략
//SELECT * FROM user WHERE username = ? AND password = ?;
//User findByUsernameAndPassword(String username, String password);

//	@Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2;", nativeQuery = true)
//	User login(String username, String password);