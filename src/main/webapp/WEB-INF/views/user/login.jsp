<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인페이지</title>
</head>
<link rel="stylesheet" href="/resource/css/style.css?${millis }" />
<body>
<div class="root">
	<div class="login-form">
		<form action="/user/login-task" method="post" class="sign-form">
			<h2>로그인</h2>
			<div style="margin: 0.4em">
				<input type="text" placeholder="아이디" name="id" required autocomplete="off" />
			</div>
			<div style="margin: 0.4em">
				<input type="password" placeholder="비밀번호" name="pass" autocomplete="off" />
			</div>	
			<c:if test="${param.error ne null }"><small style="color: red;">아이디 혹은 비밀번호가 일치하지 않습니다.</small></c:if>
			<div style="margin: 0.4em">
				<button type="submit" class="join-btn">로그인</button>
			</div>
		</form>
	</div>
	아이디가 없으신가요?<a href="/user/join"><span style="color: navy;">회원가입</span></a>
</div>
</body>
</html>