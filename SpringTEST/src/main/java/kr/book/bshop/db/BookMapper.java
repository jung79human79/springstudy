package kr.book.bshop.db;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.book.bshop.model.BookVO;

@Mapper
public interface BookMapper {

	public List<BookVO> booklist(BookVO vo);

	public void bookInsert(BookVO vo);

}
