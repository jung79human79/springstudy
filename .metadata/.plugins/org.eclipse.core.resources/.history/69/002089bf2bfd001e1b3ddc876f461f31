package com.smhrd.db;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.smhrd.model.BoardVO;

// mybatis 프레임 워크 사용할 때 interface 형식을 사용!
public interface BoardMapper {
	
	// 추상 매서드
	
	// 전체 게시글 조회 매서드
	public List<BoardVO> boardList();
	// BoardMapper mapper = new SqlSessionFactoryBean();
	// sql session 빌려오기 사용하기 반납하기 결과값 반환하기
	// mapper.boardList();
	// 모두 SqlSessionFactoryBean의 역할!!
	// 즉 스프링에서 알아서 해준다
	
	//게시글 등록 매서드
	public void register(BoardVO vo);
	// session.insert("register",vo)
	
	// 게시글 하나를 조회하는 매서드
	// mybatis 사용하는 방법
	// 1. 기존dao 방식 --> jsp/서블릿 사용했던 방식
	//2.인터페이스 방식 --> 스프링 fw 사용하는 방식
	//3. anno 방식 --> 비교적 간단한 sql 구문일때 사용권장
	
	// select * from board where idx = #{idx}
	@Select("select * from board where idx = #{idx}")
	public BoardVO boardContent(int idx);
	
	@Delete("delete from board where idx = #{idx}")
	public void boardDelete(int idx);
	
	
	
	
}