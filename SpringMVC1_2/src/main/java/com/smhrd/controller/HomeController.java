package com.smhrd.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */

// 개발자가 만든 HomeController 클래스이며 @Controller 라는 annotation 사용
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		// logger : 흐름에 대한 기록을 남긴다
		// info : 콘솔에 정보를 찍어준다 --> Welcome home 해당하는 클라이언트의 주소지를 보여줄거야
		// 요청url의 끝이 '/'들어오면 home 매서드를 실행
		logger.info("Welcome home! The client locale is {}.", locale);
		// 콘솔 출력문 : INFO : com.smhrd.controller.HomeController - Welcome home! The client locale is ko_KR.
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		// 스프링의 view resolver 가 자동으로 WEB-INF/views/+home+.jsp 로 이동
		return "home";
		// url변경 안되므로 포워드 방식으로 이동
	}
	
}
