package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

//빈 등록 : 스프링 컨테이너에서 객체를 관리 할 수 있게 하는 것
@Configuration	//빈등록(IoC관리)
@EnableWebSecurity	// 시큐리티 필터가 등록이 된다. = 스프링 시큐리티가 활성화가 되어 있는데 어떤 설정을 해당 파일에서 하겠다.(controller 가기전 먼저 필터링)
@EnableMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권환 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig {

	@Bean	///IoC로 설정 = 스프링이 관리
	public BCryptPasswordEncoder encodPWD() {
		//String encPassword = new BCryptPasswordEncoder().encode("1234");
		return new BCryptPasswordEncoder();
	}

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // 개발 중에는 CSRF 비활성화 (선택 사항)
            .authorizeHttpRequests(auth -> auth.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .requestMatchers("/","/auth/**", "/js/**", "/css/**", "/images/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/", true)
                .permitAll()
            );

        return http.build();
    }
}