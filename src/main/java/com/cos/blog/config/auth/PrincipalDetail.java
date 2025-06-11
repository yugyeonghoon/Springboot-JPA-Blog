package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료과 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.
public class PrincipalDetail implements UserDetails{
	private User user;	// <composition

	public PrincipalDetail(User user) {
		this.user = user;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	//계정이 만료되지 않았는지 리턴한다( true = 계정 만료 안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정의 잠김 여부 리턴
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	//비밀번호가 만료되었는지 여부를 알려줌
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 활성화(사용가능)인지 리턴한다. ( true = 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	//계정의 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{return "ROLE_"+user.getRole();});
		//위에 람다식 한줄이 밑에 꺼랑 동일
		return collectors;
	}
//		collectors.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				return "ROLE_"+user.getRole();	//ROLE_USER 가 리턴됨
//			}
//		});


	
	
}
