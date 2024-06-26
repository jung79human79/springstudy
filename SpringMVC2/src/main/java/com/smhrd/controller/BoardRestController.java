package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.db.BoardMapper;
import com.smhrd.model.BoardVO;
import com.smhrd.model.SearchCriteria;

@RestController
// --> @Controller + @ResponseBody
public class BoardRestController {
	// 표현 상태 전송 (representation state transfer)
	// 이 클래스는 데이터만 제공한다 --> json,xml,csv 등등  
	// 그 데이터를 web이나 모바일에서 html로 표현한다
	// 비동기 통신으로 요청이 들어올시 처리해주는 역할
	
	@Autowired  
	private BoardMapper mapper;
	
	
	
	// 자동완성 기능 만들기
		@RequestMapping("/autocomplete")
		public List<BoardVO> autocomplete (SearchCriteria criteria) {
			// 리턴타입이나 매개변수 자료형은 자유롭게 설정하지만
			// --> 내가 핸들링하기(꺼내오기) 편한 자료형으로 지정하는 것이 좋다
			List<BoardVO> list = mapper.autocomplete(criteria);
			return list;
			
		}
		
		// 비동기 통신으로 검색하는 기능
		@RequestMapping("/search")
		// --> 해당하는 매서드는 화면을 이동시키는게 아니고 결과값을 화면에 출력해주는 매서드
		// 비동기 통신에 쓰는 anno이다
		// jsp의 type ,text를 하나로 묶는 SearchCriteria 클래스 생성
		public List<BoardVO> search (SearchCriteria criteria) {  
			System.out.println("검색데이터>>"+criteria);
			List<BoardVO> list = mapper.search(criteria);
			System.out.println(list);
			System.out.println(list.size());
			// 리턴된 값이 화면에 출력된다
			// list는 주소이다 --> json구조로 변환해야한다 --> gson 사용 : 서블릿에서는 이렇게 했다
			// list에는 주소값이 담겨져 있다 우리가 비동기 통신으로 보냐줘야하는 데이터는 json구조!!
			// 주소값 -- 변환 --> json
			// 변환을 스프링에서 자동으로 진행(변환용 라이브러리 필요함 --> pom.xml)
			return list;
		}
	
	

}
