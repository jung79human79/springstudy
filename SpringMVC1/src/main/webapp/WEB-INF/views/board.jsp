<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 기본적으로 jstl/el 을 쓴다 레거시 프로젝트에는 기본적으로 jstl 라이브러리가 담아져있다 
고로 로드하는 코드만 적어준다 메이븐 dependencies 폴더를 안을 보면 jstl 라이브러리가 보인다 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="cpath" value="${pageContext.request.contextPath}"/>
<!-- == controller가져온 것임  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<!-- 이 jsp를 화면에 보이게 하려면 스프링의 ds(디스패쳐서블릿)는 항상 controller 이라는
 클래스를 실행시키게 되어있으므로 개발자는 controller클래스를 생성해야 한다 -->
	<div class="jumbotron">
		<h1>빅데이터 23차 게시판</h1>
		<p>Bootstrap을 사용하여 게시판 만들자</p>
	</div>

	<div class="container">
		<div class="card">
			<div class="card-header">게시판 연습</div>
			<div class="card-body">
				<table class="table table-bodered table-hover">
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>게시글</td>
						<td>작성일</td>
					</tr>
					<!-- jstl/el 을 사용하여 model 안에 저장된 게시글 정보를 전부 화면에 출력 -->
					<c:forEach items="${list}" var="b" >
						<tr>
							<td>${b.idx}</td>
							<td>
							
						<!-- 1.쿼리 스트링으로 데이터 보내기 
						     2. 경로상에 그냥 바로 데이터 포함해서 보내기-->
						<!-- <a href="${cpath}/boardContent?idx=${b.idx}">${b.title}</a> -->
						    <a href="${cpath}/boardContent/${b.idx}">${b.title}</a>
							</td>
							<td>${b.writer}</td>
							<td>${b.content}</td>
							<td>${b.inDate}</td>
						</tr>
					</c:forEach>	
				</table>
				<!-- onclick은 get 전송방식  -->
				<button onclick="location.href='${cpath}/register'" class="btn btn-primary btn-sm">글쓰기</button>
			</div>
			<div class="card-footer">게시판Footer</div>
		</div>
	</div>
</body>
</html>