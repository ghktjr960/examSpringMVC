<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
	<h2>회원 정보 입력</h2>
	<form:form action="step3" commandName="formData">
		<p>
			<label>이메일 : <br>
			<form:input path="email"/>
			</label>
		</p>
		<p>
			<label>이름 : <br>
			<form:input path="name"/>
			</label>
		</p>
		<p>
			<label>비밀번호 : <br>
			<form:password path="password"/>
			<!-- 
			<input type="password" name="password" id="password">
			 -->
			</label>
		</p>
		<p>
			<label>비밀번호 확인 : <br>
			<form:password path="confirmPassword"/>
			<!-- 
			<input type="password" name="confirmPassword" id="confirmPassword">
			 -->
			</label>
		</p>
		<input type="submit" value="가입완료">
		<input type="reset" value="다시작성">
	</form:form>
	
</body>
</html>