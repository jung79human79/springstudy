package com.smhrd.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smhrd.myapp.entity.MemberEntity;
import com.smhrd.myapp.mapper.MemberMapper;
import com.smhrd.myapp.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired  
	private MemberMapper mapper;
	
	// index.jsp를 띄우는 메소드.
	// @RequestMapping("/") 보다 스프링부트에서는 @Get~ @Post~ Mapping 권장
	@GetMapping("/")
	public String index() {	
		return "index";	
	}
	@GetMapping("/mypage")
	public String mypage() {	
		return "mypage";	
	}


/////////////////////////////session 쓰는거/////////////////////
//	@PostMapping("/login")	
//	public String login(MemberVO vo ,HttpSession session) {
//		// spring 에서는 mapper.login(vo) 이지만
//		// boot에서는 JPA 프레임워크 사용
//		// JPA = no sql 지향
//		//JPA (mapper == repository)
//		MemberEntity entity = mapper.findByIdAndPw(vo.getId(), vo.getPw());
//		System.out.println("받아온 entity >>" + entity);
//		// entity(jpa용) --> vo (java,jsp용)로 변경
//		// 워크벤치에서 데이터 3개정도만 추가하기
//		// 받아온 사용자의 정보를 pw 빼고 전부 출력
//		
//		//model.addAttribute("entity",entity); --> 이건 아님 로그인이므로 계속 저장되야함 
//		//model : request 경량화 버전 --> 응답을 되돌려주기 전까지만 유효 --> url이 로그인 경우만 유효
//		MemberVO myData = new MemberVO(entity.getId(),entity.getPw(),entity.getName(),entity.getTel());
//		session.setAttribute("myData", myData);
//		// --> 브라우저 닫기 전까지 유효
//		return "redirect:/mypage";
//		// 요청2 응답2
//		
//	}
	
	// 다음페이지로만 정보를 가져가는 방식 --> mypage까지만~
	@PostMapping("/login")	
	public String login(MemberVO vo,RedirectAttributes rttr) {
		MemberEntity entity = mapper.findByIdAndPw(vo.getId(), vo.getPw());
		MemberVO myData = new MemberVO(entity.getId(),entity.getPw(),entity.getName(),entity.getTel());
		rttr.addAttribute("myData",myData.getId());
		// 주의점 : 들어가는 value의 형태를 string으로 변환가능한 형태로 넣아주기
		
		// redirect:/mypage?myData=test
		// login 페이지 유효
		// mypage 페이지 유효
		return "redirect:/mypage";
		// 요청2 응답2	
	}
	
	@GetMapping("/join")
	public String join() {	
		return "join";	
	}
	
	@PostMapping("/join-process")	
	public String joinProcess (MemberVO vo) { 
		System.out.println("수집된 데이터 >>" + vo);
		
		MemberEntity entity =new MemberEntity(vo);
		mapper.save(entity);
		
		// update sql구문도 save로 처리
		// save(entity)
		// --> entity안에 idx가 없으면 insert 진행
		// --> entity안에 idx가 있으면 update 진행
		
		return "redirect:/";
	}
	
	
	
	

}