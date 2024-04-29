package com.smhrd.myapp.entity;

import com.smhrd.myapp.vo.MemberVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity anno가 안나오면 jpa 라이브러리를 안가져온거다!!
@Entity // 디비에 있는 테이블을 표현하는 자료형
@Data //getter setter매소드
@AllArgsConstructor  // 모든 필드값을 받는 생성자
@NoArgsConstructor   // 기본 생성자
public class MemberEntity {
	
	public MemberEntity(MemberVO vo) {
		this.id = vo.getId();
		this.pw = vo.getPw();
		name = vo.getName();
		tel = vo.getTel();
	}

	@Id //pk 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	// jpa 쓰려면 필수 필드임
	private Long idx; //pk 선언
	
	// 유니크 ,길이 ,null허용 등등
	@Column(unique = true) //해당컬럼에 대한 설정값
	private String id;
	
	@Column(length = 200)
	private String pw;
	
	@Column(nullable = false)
	private String name;
	
	private String tel;
	
}