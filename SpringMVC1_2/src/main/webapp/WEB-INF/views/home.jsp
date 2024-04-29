<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
    <!-- el문법사용 requst 혹은 session 혹은 application 혹은 스프링의 model 이라는 저장공간의 
    밸류값을 얻고자 키값을 적용함 -->
<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
