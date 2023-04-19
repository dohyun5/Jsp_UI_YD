<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<p>Main Page</p>
	<p>Expression Language</p>
	${3>2}<br><!-- 표현식 -->
	${"Hello"}<br>
	${10-5 }<br>
	${myName}<br>
	${myName != null}
	${myName != null ? '<p>길동이 있음</p>' : '<p>길동이 없음</p>'}<br>
	
	
	
</body>
</html>