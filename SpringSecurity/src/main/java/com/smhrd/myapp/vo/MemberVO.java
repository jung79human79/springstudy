package com.smhrd.myapp.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // getter setter
@AllArgsConstructor  // 모든 필드값을 받는 생성자
@NoArgsConstructor   // 기본 생성자
public class MemberVO {
    // JAVA안에서 사용할 데이터 교환용 자료형 
	private String id;
	private String userid;
	private String pw;
	private String role;
	
	
}
