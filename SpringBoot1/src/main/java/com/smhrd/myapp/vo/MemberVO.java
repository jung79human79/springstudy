package com.smhrd.myapp.vo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // getter setter
@AllArgsConstructor  // 모든 필드값을 받는 생성자
@NoArgsConstructor   // 기본 생성자

public class MemberVO {
	
	
	private String id;
	private String pw;
	private String name;
	private String tel;

}
