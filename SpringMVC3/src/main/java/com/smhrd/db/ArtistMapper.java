package com.smhrd.db;

import java.util.List;

import com.smhrd.model.ArtistVO;

//import org.apache.ibatis.annotations.Mapper;

//@Mapper  --> 스프링 번전이 업되면서 생략가능해졌음
public interface ArtistMapper {
	
	public List<ArtistVO> artist();

}