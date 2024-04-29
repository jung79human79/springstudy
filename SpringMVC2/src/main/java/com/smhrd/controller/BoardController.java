package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.db.BoardMapper;
import com.smhrd.model.BoardVO;
import com.smhrd.model.SearchCriteria;


// 동기 통신으로 요청이 들어올시 처리해주는 역할
@Controller 
public class BoardController {
	@Autowired  
	private BoardMapper mapper;
	
	// 글 삭제 기능
	@RequestMapping("/boardDelete/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {
		
		mapper.boardDelete(idx);
		
		return "redirect:/";
	}
	
	
	// 글 상세 조회 기능
	@GetMapping("/boardContent/{idx}")
	public String detail (@PathVariable("idx") int idx, Model model) {
		System.out.println("수집데이터>>" + idx);
		
		BoardVO result = mapper.boardContent(idx);
			
		model.addAttribute("result",result);
			
		return "content";
	}
	
	// 글쓰기 기능을 하는 매서드
	@PostMapping("/register")  
	public String register (BoardVO vo) {   	
		
		System.out.println(vo);
		
		mapper.register(vo);
		
		return "redirect:/";
		
	}
	
	
	// 글쓰기 페이지로 이동하는 매서드 생성
	@GetMapping("/register")  
	public String register () {	
		
		return "register";

	}
	
	

	// 메인페이지
	@RequestMapping("/") 
	
	public String board(Model model) {
				
		List<BoardVO> list = mapper.boardList();
		
		model.addAttribute("list",list);  
		
		
		return "board";
		
	}

	
	
}