<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<link rel="stylesheet" href="/resource/css/style.css?${millis }" />
<body>
<div class="root">
	<div class="login-form">
		<form action="/user/join-task" method="post" class="sign-form">
			<h2>로그인</h2>
			<div style="margin: 0.4em">
				<input type="text" placeholder="아이디" name="id" required autocomplete="off" />
			</div>
			<div style="margin: 0.4em">
				<input type="password" placeholder="비밀번호" name="pass" autocomplete="off" />
			</div>
			<div style="margin: 0.4em">
				<input type="text" placeholder="닉네임" name="nick" required autocomplete="off" />
			</div>
			<c:if test="${param.error eq 'r' }"><small style="color: red;">회원가입에 실패하였습니다.</small></c:if>
			<div style="margin: 0.4em">
				<button type="submit" class="join-btn">가입</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>