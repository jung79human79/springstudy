<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div class="jumbotron">
		<h1>빅데이터 23차 게시판</h1>
		<p>Bootstrap을 사용하여 게시판 만들자</p>
	</div>
	
	<div class="container">
		<div class="card">
			<div class="card-header">게시글 상세보기</div>
			<div class="card-body">
				<table class="table table-bodered table-hover table-bordered">
					<tr>
						<td>제목</td>
						<td>${result.title}</td>
					</tr>	
					<tr>
						<td>작성자</td>
						<td>${result.writer}</td>
					</tr>
					<tr>
						<td>내용</td>
						<td>${result.content}</td>
					</tr>
					<tr>
						<td>작성일</td>
						<td>${result.inDate}</td>
					</tr>
					<tr>
					<td colspan="2" align="center">
						 <button type="submit" class="btn btn-primary btn-sm" >수정</button>
						 <button onclick="location.href='${cpath}/boardDelete/${result.idx}'" class="btn btn-danger btn-sm">삭제</button>
						 <button type="submit" class="btn btn-success btn-sm">리스트</button>	
					</td>
					</tr>			
				</table>
			</div>
			
			<div class="card-footer">빅데이터 분석서비스</div>
		</div>
	</div>
</body>
</html>