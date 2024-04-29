package com.smhrd.myapp.mapper;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smhrd.myapp.entity.MemberEntity;




//@Mapper // bean으로 등록하기 위한 anno
@Repository
public interface MemberMapper extends JpaRepository<MemberEntity, Long>{
	
	// jpa 에서 제공해주는 기본 제공 crud 매서드 --> 복잡한 sql 구문은 안됨
	// 1. findall() --> select* from Member_Entity;
	// 2. findbyid(pk값)--> select* from Member_Entity where pk컬러명 = 매개변수;
	// 3. save(string id ,string pw) --> (받아올 매개변수 순서대로 혹은 Entity형태)
	// --> insert into Member_Entity values (?,?,?,?)
	// 4. delete() --> delete from Member_Entity where pk컬러명 = 매개변수;
	
	// 프로젝트에서 간단한 sql구문 --> jpa
	// 복잡한 sql 구문 --> mybatis mapper 사용
	
	// 매소드명을 기준으로 sql구문을 생성
	// select 구문 규칙
	// find + 테이블명(생략가능) + By + 컬럼명 + And(Or) + 컬럼명.....
	// 주의점 : 반드시 카멜식으로 작성해야함
	
	//SELECT * from Member_Entity
	//where ID =?
	//AND PW =?
	
	public MemberEntity findByIdAndPw(String id ,String pw);
	// 주의점 --> SELECT구문의 조건절 데이터를 채울때는 매개변수의 순서대로 채워진다!!
	// 전에 jdbc 방식

	// mapper 클래스내 기본제공 crud 매서드는 실은 안써도 된다
	// 아래코드는 안적어도 된다
	public MemberEntity save(MemberEntity entity);

	
	// spring mvc 에서는
	// sqlsessionfactorybean 구현체를 만들어서 사용
	// spring boot 에서는
	// Hibernate 구현체를 만들어서 사용
	// --> springboot컨테이너 안쪽에 해당 클래스가 등록되어있어야함.
	
	
	
}
