package com.smhrd.controller;

import java.util.List;


import org.apache.ibatis.annotations.AutomapConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.smhrd.db.BoardMapper;
import com.smhrd.model.BoardVO;

// Spring F/W 흐름
// (1) Dispatcher Servlet(Front controller) 요청을 받는다.
// (2) Handler Mapping 에게 요청값(==요청 url)을 보낸다.
// (3) Handler Mapping 요청값과 일치하는 Controller를 Spring Container에서 찾는다. 
//     --> 이 3번을 위해서 anno를 달아준 것이다
//     --> anno가 있어야 스프링 컨테이너에 적재가 되고 
//	   --> hm 이 anno 안에 요청 url이 있는지 확인한다 
// (4) Handler Adapter가 HM이 찾은 Controller를 실행

// 해당하는 클래스 파일이 Spring container 안에 자동으로 생성될 수 있게 만들고, 
// 이것이 url 매핑를 잡을 수 있는 Controller임을 명시하는 어노테이션

@Controller // anno만으로도 기능이 정의되어있음. 
public class BoardController {
	@Autowired  // 매우 중요
	// -->자동으로 연결해줘 
	// -->스프링 컨테이너에서 mapper를 구현한 구현체를 자동으로 연결해준다 
	private BoardMapper mapper;
	
	// 글 삭제 기능
	@RequestMapping("/boardDelete/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		// 1. mapper 사용하기
		mapper.boardDelete(idx);
		// 2.경로 이동 하기
		return "redirect:/";
	}
	
	
	// 1. 글 상세 조회 기능
//	@GetMapping("/boardContent")
//	public String detail (int idx) {
//		System.out.println("수집데이터>>" + idx);
		
//		return "";

//	}
	
	// 2. 글 상세 조회 기능
	// 경로상의 데이터를 직접적으로 보내주는 경우에 사용할수 있는 기능
	// /경로/{받아줄데이터이름}
	// @PathVariable("받아줄데이터이름") 자료형 변수명
	@GetMapping("/boardContent/{idx}")
	public String detail (@PathVariable("idx") int idx, Model model) {
		System.out.println("수집데이터>>" + idx);
		// 1.mapper 사용해서 게시글 한개를 조회하기
		BoardVO result = mapper.boardContent(idx);
		
		// 2. 조회한 결과를 model 담아주기
		model.addAttribute("result",result);
		
		//3. content.jsp 이동하기
		return "content";
	}
	
	//글쓰기 기능을 하는 매서드 
	@PostMapping("/register")  // --> POST 전송 벙식일때만 매서드 실행 
	public String register (BoardVO vo) {  //매개변수에 요청데이터를 받는 BoardVO을 적는다) 	
		// 1.요청데이터 수집
		// --> 스프링이 알아서 해준다
		// --> 전에 서블릿이나 dao가 했던 name값을 저장하고 정수이면 변환하고 
		// --> 데이터를 한데모으는 작업 등등의 작업을 스프링이 알아서 해준다 
		// 우리가 하는것은 매서드 매개변수로 수집하고 싶은 자료형을 지정해주기만 하면 된다.
		
		// 한개 데이터면 string타입으로 하고 name값과 같은 이름의 변수명을 만들면 된다 
		// *** vo 필드명 = 보내주는 name값 == table column명 

		System.out.println(vo);
		// 주소값 안나오고 저장되어있는 데이터 출력
		// --> @Data >> getter ,setter ,toString method override
		// toString method?
		// >> 객체의 주소값을 출력하는 대신에 안쪽에 있는 데이터 출력을 도와주는 오브젝트 매서드
		
		// 2.mapper를 사용하기
		mapper.register(vo);
		
		
		// 3.경로이동
		return "redirect:/";
		// '/'라는 경로로 이동하겠다 --> board매서드 실행 --> boardList 조회 --> 결과값 model --> board로 이동
	}
	
	
	// 글쓰기 페이지로 이동하는 매서드 생성 --> 
	@GetMapping("/register")  // --> get 전송 벙식일때만 매서드 실행
	public String register () {	
		
		return "register";

	}
	
	

	// 전에는 클래스별로 url 매핑을 했지만 (서블릿도 클래스이다)
	// Spring Framework는 url mapping 값을 메소드 단위로 설정함.
	// RequestMapping --> url mapping값을 잡아주는 방법 
	// 어떤 url로 요청하면 이 매서드를 실행시켜야 하는지 anno을 잡아주자  
	@RequestMapping("/") // "/" 요청이 들어왔을 때 board 메소드를 실행 //  따로 method를 지정한하면 get,post방식 모두가능 //서블릿의 서비스 매서드와 유사함
	// 스프링컨테이너의 ha가 써야하므로 접근가능한 public 사용
	// 반환값은 보여주는 화면의 이름이므로 문장열 타입이 반환타입
	public String board(Model model) {
			
		// model을 가져오는 방법
		// --> 메소드의 매개변수로 해당하는 객체를 작성
		// --> SC가 자동으로 객체를 채워줌!
		// new Model 처럼 객체를 생성할 필요없이 매개변수로만 잡자
		// SC에 이미 존재하는 Model이므로~
		
		// 1. DAO 생성
		// 2. DAO 사용해서 결과값 받아오기
		List<BoardVO> list = mapper.boardList();
		// 3. 받아온 결과값 model 영역에 담아주기 >> request의 경량화된 버전을 사용 : 상속을 덜 받음
		// 스프링은 원래 경량화 버전이므로~ 
		// Model 은 스프링이 기본으로 제공한다 --> 스프링 fw의 ui에서 제공하는 인터페이스
		model.addAttribute("list",list);  // ("키(=이름)",밸류(=변수))
		
		// 4. board.jsp로 forward 방식으로 이동하기
		return "board";
		// board --> 논리적인 주소값
		// WEB-INF/views/board.jsp --> 물리적인 주소값
		// 논리적인 주소값을 물리적인 주소값으로 자동으로 변환해주는 스프링 컨테이너의 View resolver 객체가 이미 존재.--> 알아서 해준다
		// ViewResolver의 역할은? (확인해보고 싶다면 servlet-context.xml 봐보기!)
		// WEB-INF/views/ + 리턴한 값 + jsp
	}

	
	
}