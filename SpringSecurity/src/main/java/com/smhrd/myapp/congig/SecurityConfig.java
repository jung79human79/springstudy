package com.smhrd.myapp.congig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.DispatcherType;

// Configuration : 해당하는 파일이 환경설정 파일임을 나타냄
// --> 해당클래스 안쪽에서 @BEAN(객체)들을 하나 이상 정의할 수 있게 해줌
// 우리의 @BEAN으로 덮어쒸운다
@Configuration
// 해강 클레스파일이 "spring SECURITY 용 환경설정 파일임을 나타냄
@EnableWebSecurity
public class SecurityConfig {
	
	// Bean(객체)
	// : 스프링컨테이너에 적재될수 있는 형태
	// 언제 사용하나?
	// 1.기능들을 사용자 정의형태로 만들어야 할때 사용
	// 2. 모든 클래스 파일들이 공유해야할 때 --> @Autowired로 가능
	
	// 클래스안에 또 다른 클래스 설계가 가능
	// --> bean 이 객체 즉 클래스 --> inner class
	
	
	// 거름망 생성
	// throws Exception == try 캐취문
	
	// 권한을 부여하는 3가지 메서드
	// (1) permitAll : 전부 허용
	// (2) hasAnyRole : 권한 확인 후 해당 권한을 가진 사람레게만 허용
	// (3) authenticated : 인증받은 사용자에게만 허용
	
	@Bean  // 객체를 등록
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		// 해당하는 매서드가 동작하면서 SecurityFilterChain 생성할 거고 bean으로써 등록
		// 디폴트 SecurityFilterChain에 bean을 덮어쒸운다
		
		// deprecated : 더 이상 지원하지 않는 문법들 --> 사라질 문법 --> 버전?? (함수명에 가로줄)
		// http.cors();  --> 동일출처 정책 해지
		
		// 스프링시큐리티에서 매개변수로써 권장하는 문법
		//--> lambda식 == 익명함수(해당하는 영역에서만 사용하고 버릴함수) 개념
		//--> 어나니머스 매소드
		// **lambda식 문법 : ()->실해할 구문	
		// 원래 lambda식은 http.cors(()->)); 
		// 객체::매서드 --> lambda식에서 제공해주는 method reference 연산자
		// 1.cors (cross orign resource sharing) : 동일출처정책 비활성화 하겠다
		http.cors(AbstractHttpConfigurer::disable)
		// 2.csrf(cross site request forgery) 비활성화 하겠다  
		.csrf(AbstractHttpConfigurer::disable)
		// 3.우리가 인가를 내려줄 페이지를 지정하는 매서드 --> 흐름에 맞게 순서대로 써야함
		.authorizeHttpRequests((request)-> 
			request.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
			.requestMatchers("/images/**","/join").permitAll()
			.requestMatchers("/admin").hasAnyRole("admin")
			.requestMatchers("/user").hasAnyRole("user")
			// 인증받은 대상만 허용
			.anyRequest().authenticated())
		// 4.사용자 지정페이지로 인증받을 수 있도록 매소드 설정
		.formLogin((login)->
				login.loginPage("/")
				//@PostMapping
				.loginProcessingUrl("/login-process")
				.usernameParameter("userid")
				.passwordParameter("pw")
				// build된 user == userid,pw
				// redirect:/dashboard
				.defaultSuccessUrl("/dashboard")
				.permitAll());
				//.logout(null)	
				//.sessionManagement(null);
				
		// 위1.2번은(http프로토콜관련)는 기본 옵션임 : 정책에 대한 허용
		// security는 반드시 post방식
		return http.build();	
	}
	// 암호화를 도와주는 PasswordEncoder 하나 추가
	// -> db에 있는 데이터를 가져오려고 했더니,암호화 하는 Encoder~~ 에러 발생
	@Bean
	PasswordEncoder passwordEncoder() {
		// 이미 다 만즐어진 암호화 알고리즘 사용하겠다~
		return new BCryptPasswordEncoder();
	}
	
	
	

}