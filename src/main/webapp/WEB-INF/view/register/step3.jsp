<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="member.register"/></title>
</head>
<body>
	<p><spring:message code="register.done" arguments="${formData.name}"/></p>
	<!-- 
		arguments="${formData.name}는 properties파일에서 정의한  내용 중 register.done의 {0}에 해당하는 내용이다
		register.done의 {0}은 배열의 형태로 값을 전달받고 전달받은 값을 인덱스 번호에 맞춰 출력하는데 해당 내용은 0번째 인덱스의
		값을 가져오는 것을 지정한것이다.
		이때 arguments="문자열1", "문자열2", "문자열3", ... 과 같이 배열의 구분을 ,로 하여 값을 보낸다.
	-->
	<p><a href="<c:url value='/'/>"><spring:message code="go.main"/></a>
</body>
</html>