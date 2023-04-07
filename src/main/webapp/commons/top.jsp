<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 서비스</title>
</head>
<link rel="stylesheet" href="/resource/css/style.css?${millis }" />
<body>
<header class="header">
 	<div class="header-left">
		<div class="header-container" style="justify-content: space-between;">
			 <div class="user-info">
				<a href="/">홈</a> <a href="/board/create">게시글작성</a>
			</div>
		</div>
	</div>
	<div class="header-right">
	<c:choose>
		<c:when test="${sessionScope.logon }">
		 <div class="top-bar__right">
			<div>
				<b>${sessionScope.logonUser.nick }</b>님 환영합니다.
			</div>
			<div class="login-button">
				<div style="padding: 10px 20px;">
					<a href="/user/logout">로그아웃</a>
				</div>
			</div>
		</div>
		</c:when>
		<c:otherwise>
			 <div class="top-bar__right">
				<div style="padding: 10px 20px;">
					<a href="/user/login">로그인</a>
				</div>
			</div>
		</c:otherwise>
</c:choose>
</div>
</header>
