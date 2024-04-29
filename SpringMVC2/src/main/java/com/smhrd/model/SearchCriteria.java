package com.smhrd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
	// 검색 기준을 설정하는 객체
	// cf) 태그의 속성 중 name값은 자바 name을 제외한 속성은(class,id등등) js/css/html 에서 사용
	private String type;
	private String text;
	
	
	
	

}
