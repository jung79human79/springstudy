package com.smhrd.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.smhrd.myapp.entity.Member;
import com.smhrd.myapp.mapper.MemberRepo;
import com.smhrd.myapp.vo.MemberVO;


@Controller
public class SeviceController {
	@Autowired 
	private MemberRepo mapper;
	@Autowired
	private PasswordEncoder encoder; // --> SecurityConfig에 만들어져 있음 
	
	// 다음페이지로만 정보를 가져가는 방식 --> dashboard까지만~
		@PostMapping("/login-process")
		public String loginProcess(MemberVO vo) {
			Member entity = mapper.findByUseridAndPw(vo.getUserid(), vo.getPw());
			// findByIdAndPw :카멜식 기법
			if(entity!=null){
				return "redirect:/dashboard";
			}
			return "redirect:/";
				
		}
		
		// 다음페이지로만 정보를 가져가는 방식 --> dashboard까지만~
		@PostMapping("/join-process")
		public String joinProcess(MemberVO vo) {
		Member entity = new Member(vo.getUserid(),vo.getPw(),"user",encoder);
		mapper.save(entity);		
		return "redirect:/";
						
	}

}
