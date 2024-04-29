package com.smhrd.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smhrd.myapp.entity.Member;
import com.smhrd.myapp.mapper.MemberRepo;
import com.smhrd.myapp.vo.MemberVO;

//import com.smhrd.myapp.mapper.MemberMapper;

@Controller // 자동으로 스프링컨테이너에 들어감
//@RequestMapping("/view")  // -->클래스 단위로 url mapping 부여 가능
public class ViewController {
	
	@Autowired 
	private MemberRepo mapper;
	
	@GetMapping("/")
	public String login() {	
		return "login";	
	}
	
	@GetMapping("/join")
	public String join() {	
		return "join";	
	}
	
	@GetMapping("/admin")
	//@PreAuthorize("hasAnyRole('admin')")
	public String admin() {	
		return "admin";	
	}
	
	@GetMapping("/user")
	public String user() {	
		return "user";	
	}
	
	@GetMapping("/dashboard")
	public String dashboard() {	
		return "dashboard";	
	}
	
	
	
	
}