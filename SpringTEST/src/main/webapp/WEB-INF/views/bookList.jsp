<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="cpath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>보유 도서 현환</h1>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작가</td>
			<td>출판사</td>
			<td>ISBN</td>
			<td>보유도서수</td>
		</tr>
		<c:forEach items="${list}" var="b">
			<tr>
				<td>${b.num}</td>
				<td>${b.title}</td>
				<td>${b.author}</td>
				<td>${b.company}</td>
				<td>${b.isbn}</td>
				<td>${b.count}</td>
			</tr>
		</c:forEach>
	</table>
	<h1>도서 입력</h1>
	<form action="${cpath}/bookInsert.do" method="post">
		제목 : <input name= "title" type="text"><br>
		작가 : <input name= "author" type="text"><br>
		출판사 : <input name= "company" type="text"><br>
		ISBN : <input name= "isbn" type="text"><br>
		<!-- count컬럼 즉 필드는 타입이 int 이므로 숫자를 입력해야함 -->
		보유도서수 : <input name= "count" type="text"><br>	
		<input type="submit" value="등록">			
	</form>
</body>
</html>