package com.smhrd.model;


import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // getter setter
@AllArgsConstructor  // 모든 필드값을 받는 생성자
@NoArgsConstructor   // 기본 생성자

public class ArtistVO {
	private Date collectDate;  // java.sql로 자동완성
	private String channel;
	private String artist;
	private String keyword;
	private long keywordCnt;  // 클릭수는 단위가 크다 고로 실수형 자료

}