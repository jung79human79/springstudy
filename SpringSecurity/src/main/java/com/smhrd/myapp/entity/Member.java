package com.smhrd.myapp.entity;


import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Entity anno가 안나오면 jpa 라이브러리를 안가져온거다!!
@Entity // 디비에 있는 테이블을 표현하는 자료형
@Data //getter setter매소드
@AllArgsConstructor  // 모든 필드값을 받는 생성자
@NoArgsConstructor   // 기본 생성자

public class Member {
	

	
	
	
	public Member(String userid, String pw, String role, PasswordEncoder encoder) {
		this.userid = userid;
		this.pw = encoder.encode(pw);
		this.role = role;
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private Long id; //pk
	@Column(nullable = false)
	private String userid; //not null
	@Column(nullable = false)
	private String pw;  //not null
	@Column(updatable = false,nullable = false) // updatable = false 수정불가
	private String role; // 역할 ,권한 설정때 사용예정
	                     // 디폴트 값 'user'

}
