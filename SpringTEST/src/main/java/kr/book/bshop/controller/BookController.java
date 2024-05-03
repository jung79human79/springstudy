package kr.book.bshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.book.bshop.db.BookMapper;
import kr.book.bshop.model.BookVO;

@Controller
public class BookController {
	@Autowired  
	private BookMapper mapper;
	
	@RequestMapping("/bookList.do")
	public void booklist(BookVO vo,Model model) {
		// 리턴값이 없는데 bookList.jsp 파일로 가는 이유가?
		// web.xml에서 <url-pattern>/</url-pattern> 으로 정하면 반드시 스트링반환으로 리턴값을 해당프로젝트에 jsp파일명을 적어여한다
		// 근데 void인 경우에는 전에 서블릿에서 FC가 해주던 gomain.do에서 main.jsp로 가듯이 그것을 스프링의 DispatcherServlet이 해준다고 보면 된다
		 
		List<BookVO> list = mapper.booklist(vo);
		model.addAttribute("list",list);
		//return "bookList"; 포워드방식으로 가는것이다
	}
	
	@RequestMapping("/bookInsert.do")
	public String bookInsert(BookVO vo) {
		
		mapper.bookInsert(vo);
		
		return "redirect:/bookList.do";
	}
}
