<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/top.jsp"%>
<div class="root">
	<div class="login-form">
	비밀번호를 입력해주세요
	<form action="/action/passCheckModify-task" method="post">
		<input type="hidden" name="code" value="${code }">
		<div style="margin: 0.4em">
			<input type="password" placeholder="비밀번호" name="pass" autocomplete="off" />
		</div>
		<div style="margin: 0.4em">
			<button type="submit" class="join-btn">확인</button>
		</div>
	</form>
	</div>
</div>
</body>
</html>