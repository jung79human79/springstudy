package com.smhrd.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// lombok 라이브러리란 --> getter setter 생성자 메서드를 anno을 활용하여 자동으로 생성
@Data   // getter setter
@AllArgsConstructor  // 모든 필드값을 받는 생성자
@NoArgsConstructor   // 기본 생성자
public class BoardVO {
	// 게시글 하나를 표현할 수있는 나만의 자료형

	private int idx;
	private String title;
	private String writer;
	private String content;
	private Date inDate;  //java.util로 Date 를 자동완성 // 자바에서 기본으로 재공
	
	
	
	

	
	
	
}