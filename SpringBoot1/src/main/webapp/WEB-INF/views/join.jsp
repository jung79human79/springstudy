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
    <!-- /join-process로 데이터를 전송하면 데이터를 추가 jpa사용해서~~
    그리고 index.jsp로 이동 -->
	<form action="${cpath}/join-process" method="post">
	id : <input name = "id" type = "text">
	<br>
	pw : <input name = "pw" type = "password">
	<br>
	name : <input name = "name" type = "text">
	<br>
	tel : <input name = "tel" type = "text">
	<br>
	
	<input type = "submit" value = "회원가입">
	</form>
</body>
</html>