package com.smhrd.myapp.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.myapp.entity.Member;



@Repository
public interface MemberRepo extends JpaRepository<Member,Long>{

	Member findByUseridAndPw(String userid, String pw);
		//누구를 상속받나 ? interface인데 구현이 아닌 상속받겠다
		// 기본 crud세팅 끝남.

	Member findByUserid(String username);

		
		//public Member findByIdAndPw(String id ,String pw);
		
		//public Member save(Member entity);
	}

