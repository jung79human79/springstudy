package com.smhrd.db;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.smhrd.model.BoardVO;
import com.smhrd.model.SearchCriteria;

public interface BoardMapper {
	
	
	public List<BoardVO> boardList();
	
	
	public void register(BoardVO vo);
	

	@Select("select * from board where idx = #{idx}")
	public BoardVO boardContent(int idx);
	
	@Delete("delete from board where idx = #{idx}")
	public void boardDelete(int idx);

	
	public List<BoardVO> search(SearchCriteria criteria);


	public List<BoardVO> autocomplete(SearchCriteria criteria);






	
	
	
	
}